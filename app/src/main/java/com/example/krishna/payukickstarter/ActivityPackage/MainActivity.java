package com.example.krishna.payukickstarter.ActivityPackage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.krishna.payukickstarter.AdapterPackage.MyCustomAdapter;
import com.example.krishna.payukickstarter.DataClasses.ProjectDetails;
import com.example.krishna.payukickstarter.Main2Activity;
import com.example.krishna.payukickstarter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA="http://starlord.hackerearth.com/kickstarter";
    private static final String TAG = "Ankit";
//    public ArrayList<ProjectDetails> list = new ArrayList<>();
    public static ArrayList<ProjectDetails> list;
    private ListView listView;
    private MyCustomAdapter mySessionAdapter;
    ProgressDialog progressDialog;
    ArrayList<ProjectDetails> tempList = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_settings){
            Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
        }else if (id==R.id.menuSortAlphabet){
            Collections.sort(list, new Comparator<ProjectDetails>() {
                @Override
                public int compare(ProjectDetails projectDetails, ProjectDetails t1) {
                    return projectDetails.getTitle().compareTo(t1.getTitle());
                }
            });
            Collections.sort(tempList, new Comparator<ProjectDetails>() {
                @Override
                public int compare(ProjectDetails projectDetails, ProjectDetails t1) {
                    return projectDetails.getTitle().compareTo(t1.getTitle());
                }
            });
            mySessionAdapter.notifyDataSetChanged();
            Toast.makeText(this, "sorted Alphabetically", Toast.LENGTH_LONG).show();


        }else if (id==R.id.menuSortCreator){
            Collections.sort(list, new Comparator<ProjectDetails>() {
                @Override
                public int compare(ProjectDetails projectDetails, ProjectDetails t1) {
                    return projectDetails.getBy().compareTo(t1.getBy());
                }
            });
            mySessionAdapter.notifyDataSetChanged();
            Toast.makeText(this, "sorted by creator", Toast.LENGTH_LONG).show();


        }else if (id==R.id.menuSortAmntPledged){
            Collections.sort(list, new Comparator<ProjectDetails>() {
                @Override
                public int compare(ProjectDetails projectDetails, ProjectDetails t1) {
                    return projectDetails.getTitle().compareTo(t1.getAmtPledged());
                }
            });
            mySessionAdapter.notifyDataSetChanged();
            Toast.makeText(this, "sorted by Amount Pledged", Toast.LENGTH_LONG).show();


        }else if (id==R.id.menuSortBackers){
            Collections.sort(list, new Comparator<ProjectDetails>() {
                @Override
                public int compare(ProjectDetails projectDetails, ProjectDetails t1) {
                    return projectDetails.getNumBackers().compareTo(t1.getNumBackers());
                }
            });
            mySessionAdapter.notifyDataSetChanged();
            Toast.makeText(this, "sorted by Backers", Toast.LENGTH_LONG).show();


        }else if (id==R.id.menuSortCountry){
            Collections.sort(list, new Comparator<ProjectDetails>() {
                @Override
                public int compare(ProjectDetails projectDetails, ProjectDetails t1) {
                    return projectDetails.getCountry().compareTo(t1.getCountry());
                }
            });
            mySessionAdapter.notifyDataSetChanged();
            Toast.makeText(this, "sorted by Country", Toast.LENGTH_LONG).show();


        }else if (id==R.id.menuSortLocation){
            Collections.sort(list, new Comparator<ProjectDetails>() {
                @Override
                public int compare(ProjectDetails projectDetails, ProjectDetails t1) {
                    return projectDetails.getLocation().compareTo(t1.getLocation());
                }
            });
            mySessionAdapter.notifyDataSetChanged();
            Toast.makeText(this, "sorted by Location", Toast.LENGTH_LONG).show();


        }else if (id==R.id.menuSortType){
            Collections.sort(list, new Comparator<ProjectDetails>() {
                @Override
                public int compare(ProjectDetails projectDetails, ProjectDetails t1) {
                    return projectDetails.getType().compareTo(t1.getType());
                }
            });
            mySessionAdapter.notifyDataSetChanged();
            Toast.makeText(this, "sorted by Type", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("saving data...");
        progressDialog.show();

        FetchData fetchData = new FetchData();
        fetchData.execute();

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                String send = String.valueOf(i);
                intent.putExtra("POS", send);
                startActivity(intent);

            }
        });

        Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                //get the size of the tempList and save it in size variable
                int size = tempList.size() + 10;
        //clear the tempList arraylist and readd the data otherwise sorting will not work properly fir the listView
                tempList.clear();

                //add all the element in templist from 0 to previous size+10
                for (int i = 0; i <size; i++) {
                    tempList.add(list.get(i));
                }

                mySessionAdapter = new MyCustomAdapter(tempList, MainActivity.this);
                listView.setAdapter(mySessionAdapter);
                setListViewHeightBasedOnChildren(listView);

            }
        });





    }



    public class FetchData extends AsyncTask<Void,Void,Void> {
        //String data is the whole JSON file
        String data="";
//        public ArrayList<ProjectDetails> list;

        private static final String TAG = "Ankit";


        //this will fetch the data form url in the background
        @Override
        protected Void doInBackground(Void... voids) {


            try {
                URL url = new URL("http://starlord.hackerearth.com/kickstarter");
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line = "";
                    while (line != null) {
                        line = bufferedReader.readLine();
                        data = data + line;
//                    Log.i(TAG, "doInBackground: "+data);

                    }

                    JSONArray jsonArray = new JSONArray(data);
                    Log.i(TAG, "doInBackground: "+jsonArray.length());

                    list = new ArrayList<ProjectDetails>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject row = jsonArray.getJSONObject(i);
//                    Log.i(TAG, "doInBackground: "+row);
                        ProjectDetails pd = new ProjectDetails();
                        for (int j = 0; j < row.length(); j++) {
                            pd.setSno(String.valueOf(row.get("s.no")));
                            pd.setAmtPledged(String.valueOf(row.get("amt.pledged")));
                            pd.setBlurb(String.valueOf(row.get("blurb")));
                            pd.setBy(String.valueOf(row.get("by")));
                            pd.setCountry(String.valueOf(row.get("country")));
                            pd.setCurrency(String.valueOf(row.get("currency")));
                            pd.setEndTime(String.valueOf(row.get("end.time")));
                            pd.setLocation(String.valueOf(row.get("location")));
                            pd.setPecentageFunded(String.valueOf(row.get("percentage.funded")));
                            pd.setNumBackers(String.valueOf(row.get("num.backers")));
                            pd.setState(String.valueOf(row.get("state")));
                            pd.setTitle(String.valueOf(row.get("title")));
                            pd.setType(String.valueOf(row.get("type")));
                            pd.setUrl(String.valueOf(row.get("url")));
                        }
                        list.add(pd);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i(TAG, "onPostExecute: "+list.size());

            list = list;
            progressDialog.dismiss();
            //initialize ListView and sent the data using adapter
//            ArrayList<ProjectDetails> temp = new ArrayList<>();
            for (int i = 0; i <10 ; i++) {
                tempList.add(list.get(i));
            }
            mySessionAdapter = new MyCustomAdapter(tempList, MainActivity.this);
            listView.setAdapter(mySessionAdapter);

            setListViewHeightBasedOnChildren(listView);

        }
    }



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}

