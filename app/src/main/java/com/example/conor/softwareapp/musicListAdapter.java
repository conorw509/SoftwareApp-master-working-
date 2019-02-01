package com.example.conor.softwareapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.IOException;
import java.util.ArrayList;


public class musicListAdapter extends ArrayAdapter<music> {

    private Context mContext;
    private int mResource;
    private int lastPos = -1;
    private MediaPlayer mediaPlayer;
    private boolean flag = true;

    static class ViewHolder {
        TextView name;
        TextView artist;
        ImageView start_pause;
        ImageView stop;

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
        //String start_pause = getItem(position).getImagePlay_Pause();
        //String stop = getItem(position).getImageStop();

        final View result;
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.view1);
            holder.artist = (TextView) convertView.findViewById(R.id.view2);
            holder.start_pause = (ImageView) convertView.findViewById(R.id.view3);
            holder.stop = (ImageView) convertView.findViewById(R.id.view4);

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
        holder.start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    holder.start_pause.setImageResource(R.drawable.pause);

                } else {
                    mediaPlayer.start();
                    holder.start_pause.setImageResource(R.drawable.play_btn);
                }
                mediaPlayer.start();
            }
        });
        holder.stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag = true;
                }
                holder.stop.setImageResource(R.drawable.stop_blue);
            }
        });
        return convertView;

    }
/*
    private void setUpImageLoader(){


        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
        mContext);
        builder.defaultDisplayImageOptions(defaultOptions);
        builder.memoryCache(new WeakMemoryCache());
        builder.discCacheSize(100 * 1024 * 1024);
        ImageLoaderConfiguration config = builder.build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }*/
}
