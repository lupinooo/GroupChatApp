package com.example.proiectandroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomAdapter extends ArrayAdapter {

    private final Activity context;
    private final Integer[] images;
    private final String[] options;

    public CustomAdapter(Activity context, Integer[] images, String[] options) {
        super(context, R.layout.listview_row, options);
        this.context=context;
        this.images=images;
        this.options=options;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row, null, true);
        ImageView iv=(ImageView)rowView.findViewById(R.id.imageViewRow);
        TextView tv=(TextView)rowView.findViewById(R.id.textViewRow);

        iv.setImageResource(images[position]);
        tv.setText(options[position]);


        return rowView;
    }
}
