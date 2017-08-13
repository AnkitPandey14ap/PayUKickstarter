package com.example.krishna.payukickstarter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.example.krishna.payukickstarter.ActivityPackage.MainActivity;

public class Main2Activity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    String received="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent intent = getIntent();
        received = intent.getStringExtra("POS");
        int pos = Integer.parseInt(received);

        // Create the adapter that will return a fragment for each of the list of
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(pos);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            TextView titleTextView = (TextView) rootView.findViewById(R.id.titleTextView);
            final int i = getArguments().getInt(ARG_SECTION_NUMBER);

            TextView blurb= rootView.findViewById(R.id.blurb);
            TextView byTextView= rootView.findViewById(R.id.byTextView);
            TextView backersTextView = rootView.findViewById(R.id.backersTextView);
            TextView pledgedTextView = rootView.findViewById(R.id.pledgedTextView);
            TextView locationTextView= rootView.findViewById(R.id.locationTextView);
//            TextView t = rootView.findViewById(R.id.titleTextView);
            Button visit= rootView.findViewById(R.id.visit);
            visit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("https://www.kickstarter.com/"+MainActivity.list.get(i).getUrl()));
                    startActivity(intent);

                }
            });


            titleTextView.setText(MainActivity.list.get(i).getTitle());

            blurb.setText(MainActivity.list.get(i).getBlurb());
            byTextView.setText(MainActivity.list.get(i).getBy());
            backersTextView.setText(MainActivity.list.get(i).getNumBackers()+" backers pledged"+ MainActivity.list.get(i).getCurrency()+" "+ MainActivity.list.get(i).getAmtPledged() +"to help bring this project to life.");
            pledgedTextView.setText(MainActivity.list.get(i).getCurrency()+" "+MainActivity.list.get(i).getAmtPledged());
            locationTextView.setText(MainActivity.list.get(i).getLocation()+","+MainActivity.list.get(i).getCountry());

            return rootView;
        }

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return MainActivity.list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            String s = String.valueOf(position);
            return s;
            /*switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }*/


        }
    }
}
