package com.example.conor.softwareapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class musicListAdapter extends ArrayAdapter<music> {

private Context mContext;
private int mResource;

    public musicListAdapter(Context context, int resource,ArrayList<music> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource =resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String songName = getItem(position).getSongName();
        String artist = getItem(position).getArtist();

        music music = new music(songName,artist);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView textName = (TextView) convertView.findViewById(R.id.view1);
        TextView  textArt = (TextView)convertView.findViewById(R.id.view2);

        textName.setText(songName);
        textArt.setText(artist);

        return  convertView;

    }
}
