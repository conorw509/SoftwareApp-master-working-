package com.example.conor.softwareapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.model.music;
import java.util.ArrayList;

public class musicListAdapter extends ArrayAdapter<music> {

    private Context mContext;
    private int mResource;
    private int lastPos = -1;

    static class ViewHolder {
        TextView name;
        TextView artist;
    }

    public musicListAdapter(Context context, int resource, ArrayList<music> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String songName = getItem(position).getSongName();
        String artist = getItem(position).getArtist();

        final View result;
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.view1);
            holder.artist = (TextView) convertView.findViewById(R.id.view2);

            result = convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPos) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPos = position;
        holder.name.setText(songName);
        holder.artist.setText(artist);
        return convertView;
    }
}
