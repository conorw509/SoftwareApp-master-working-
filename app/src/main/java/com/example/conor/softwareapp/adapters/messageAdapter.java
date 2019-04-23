package com.example.conor.softwareapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.model.messages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class messageAdapter extends RecyclerView.Adapter<messageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private Context mContext;
    private List<messages> messageList;
    private FirebaseUser firebaseUser;
    private String ImgUrl;

    public messageAdapter(Context mContext, List<messages> messages, String imgUrl) {
        this.mContext = mContext;
        this.messageList = messages;
        this.ImgUrl = imgUrl;
    }

    @NonNull
    @Override
    public messageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, viewGroup, false);
            return new messageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, viewGroup, false);
            return new messageAdapter.ViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull messageAdapter.ViewHolder viewHolder, int i) {
        messages messages = messageList.get(i);
        viewHolder.show_msg.setText(messages.getMsg());

        if (i == messageList.size() - 1) {
            if (messages.isSeen()) {
                viewHolder.txtSeen.setText("Seen");
            }
            if (!messages.isSeen()) {
                viewHolder.txtSeen.setText("Delivered");
            }
        } else {
            viewHolder.txtSeen.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSeen;
        public TextView show_msg;
        public ImageView proile_img;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSeen = itemView.findViewById(R.id.seenTxt);
            itemView.setTag(txtSeen);
            show_msg = itemView.findViewById(R.id.show_msg);
            proile_img = itemView.findViewById(R.id.profileImg);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (messageList.get(position).getSend().equals(firebaseUser.getUid())) {

            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }

    }
}


