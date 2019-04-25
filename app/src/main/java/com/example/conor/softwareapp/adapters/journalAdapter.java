package com.example.conor.softwareapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.model.journalContent;
import com.example.conor.softwareapp.players.journal_item_view;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;

public class journalAdapter extends RecyclerView.Adapter<journalAdapter.MyViewHolder> {
    private List<journalContent> journalList;
    private Context mContext;
    private FirebaseUser firebaseUser;


    public journalAdapter(Context mContext, List<journalContent> journalList) {
        this.mContext = mContext;
        this.journalList = journalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.journal_view, parent, false);
        return new journalAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final journalContent content = journalList.get(position);
        holder.date.setText(content.getDate());
        holder.content.setText(content.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, journal_item_view.class);
                intent.putExtra("date", content.getDate()).putExtra("content", content.getContent());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, entryName, content;

        public MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.dateV);
            content = view.findViewById(R.id.journalContent);

        }
    }
}



