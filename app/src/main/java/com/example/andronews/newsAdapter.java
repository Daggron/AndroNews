package com.example.andronews;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class newsAdapter extends ArrayAdapter<news> {
    public newsAdapter(Context context, int resource, ArrayList<news> news) {
        super(context, 0,news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.news,parent,false);
        }
        news eachnews=getItem(position);

        TextView title=(TextView) convertView.findViewById(R.id.title);
        title.setText(eachnews.getTitle());

        TextView tag = convertView.findViewById(R.id.tag);
        tag.setText(eachnews.getTag());

        TextView date = convertView.findViewById(R.id.date);
        date.setText(eachnews.getDate());

        TextView author = convertView.findViewById(R.id.author);
        author.setText(eachnews.getAuthor());

        convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backdrop));

        return convertView;
    }
}
