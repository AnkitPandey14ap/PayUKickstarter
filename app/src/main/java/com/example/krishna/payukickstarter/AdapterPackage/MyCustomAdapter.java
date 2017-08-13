package com.example.krishna.payukickstarter.AdapterPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.krishna.payukickstarter.DataClasses.ProjectDetails;
import com.example.krishna.payukickstarter.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by krishna pandey on 16-07-2017.
 */

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private final ArrayList<ProjectDetails> list;
    private final Context context;

    public MyCustomAdapter(ArrayList<ProjectDetails> list, Context context) {
        this.list = list;

        this.context = context;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view = inflater.inflate(R.layout.item_view, null);
            view = inflater.inflate(R.layout.my_project_item, null);
        }

        TextView sno = (TextView) view.findViewById(R.id.snoTextView);
        TextView title = (TextView) view.findViewById(R.id.titleTextView);
        TextView by = (TextView) view.findViewById(R.id.byTextView);
        TextView backers = (TextView) view.findViewById(R.id.backersTextView);
        TextView endTime = (TextView) view.findViewById(R.id.endTextView);

//        int n = Integer.parseInt(list.get(position).getSno());
//        String s = String.valueOf(n + 1);
        sno.setText(String.valueOf(position+1));
        title.setText(list.get(position).getTitle());
        by.setText("By: - "+list.get(position).getBy());
        backers.setText("Backers: -"+list.get(position).getNumBackers());
        endTime.setText(list.get(position).getEndTime());

        return view;
    }



}
