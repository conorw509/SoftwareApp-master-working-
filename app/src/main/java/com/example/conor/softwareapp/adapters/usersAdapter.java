package com.example.conor.softwareapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.players.messageActivity;
import com.example.conor.softwareapp.model.User;
import com.example.conor.softwareapp.model.messages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.List;

public class usersAdapter extends RecyclerView.Adapter<usersAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUser;
    private boolean isChat;

    public usersAdapter(Context mContext, List<User> mUser, boolean isChat) {
        this.mContext = mContext;
        this.mUser = mUser;
        this.isChat = isChat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.users_view, viewGroup, false);
        return new usersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final User user = mUser.get(i);
        viewHolder.userName.setText(user.getUserName());
        Glide.with(mContext).load(user.getImageUrl()).into(viewHolder.proile_img);

        if (isChat) {
            if (user.getStatus().equals("online")) {
                viewHolder.imgOn.setVisibility(View.VISIBLE);
                viewHolder.imgOff.setVisibility(View.GONE);
            } else {
                viewHolder.imgOn.setVisibility(View.GONE);
                viewHolder.imgOff.setVisibility(View.VISIBLE);
            }
        } else {
            viewHolder.imgOn.setVisibility(View.GONE);
            viewHolder.imgOff.setVisibility(View.GONE);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, messageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }
    
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView userName;
        public ImageView proile_img;
        private ImageView imgOn;
        private ImageView imgOff;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userView);
            proile_img = itemView.findViewById(R.id.profileImg);
            imgOn = itemView.findViewById(R.id.imgOn);
            imgOff = itemView.findViewById(R.id.imgOff);
        }
    }
}