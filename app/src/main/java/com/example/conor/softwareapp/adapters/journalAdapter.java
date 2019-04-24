package com.example.conor.softwareapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.conor.softwareapp.R;
import com.example.conor.softwareapp.model.journalContent;
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
        journalContent content = journalList.get(position);

//        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        long post_time = content.getDate();
//        Date date = null;
//        try {
//            date = new Date(post_time);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        holder.date.setText(content.getDate());
        holder.contents.setText(content.getContent());
        holder.tag.setText(content.getTag());
    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, contents, tag;

        public MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.dateView);
            contents = view.findViewById(R.id.journalContent);
            tag = view.findViewById(R.id.tags);

        }
    }
}



