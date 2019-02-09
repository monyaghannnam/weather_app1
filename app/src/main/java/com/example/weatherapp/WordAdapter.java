package com.example.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    Word current;


    public WordAdapter(@NonNull Context context, ArrayList<Word> words) {
        super(context, 0, words);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView =convertView;
        // Check if the existing view is being reused, otherwise inflate the view
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

// Get the {@link AndroidFlavor} object located at this position in the list
        current=getItem(position);

        TextView day=(TextView)listItemView.findViewById(R.id.day);
        day.setText(current.getDay());

        TextView weather=(TextView)listItemView.findViewById(R.id.weather);
        weather.setText(current.getWeather());

        TextView temp=(TextView)listItemView.findViewById(R.id.tempreature);
        temp.setText(current.getTemp());

        ImageView img=(ImageView)listItemView.findViewById(R.id.list_img);
        Picasso.get().load(current.getImgUrl()).into(img);
// resize(100, 100).
        img.setVisibility(View.VISIBLE);



        return listItemView;

    }
}