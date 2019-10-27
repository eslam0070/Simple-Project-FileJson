package com.egyeso.workonfilejson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ReviewsAdapter extends ArrayAdapter<Reviews> {


    public ReviewsAdapter(Context context, int resource, ArrayList<Reviews> objects) {
        super(context, resource, objects);
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_review , parent ,false);

        TextView textView = convertView.findViewById(R.id.textView5);
        TextView textView1 = convertView.findViewById(R.id.textView6) ;

        textView.setText((getItem(position)).getUsername());
        textView1.setText(""+ getItem(position).getRate());

        return convertView;
    }
}
