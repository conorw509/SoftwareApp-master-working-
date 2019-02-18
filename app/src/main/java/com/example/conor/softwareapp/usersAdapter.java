package com.example.conor.softwareapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class usersAdapter extends RecyclerView.Adapter<usersAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUser;

    public usersAdapter(Context mContext, List<User> mUser) {
        this.mContext = mContext;
        this.mUser = mUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(mContext).inflate(R.layout.users_view,viewGroup,false);
        return new usersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        User user = mUser.get(i);
        viewHolder.userName.setText(user.getUserName());
       // if(user.getImgageUrl().equals("default")) {
            viewHolder.proile_img.setImageResource(R.drawable.ic_person_black_24dp);
       // }else{
       //     Glide.with(mContext).load(user.getImgageUrl()).into(viewHolder.proile_img);
       // }
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public ImageView proile_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userView);
            proile_img = itemView.findViewById(R.id.profileImg);
        }
    }
}