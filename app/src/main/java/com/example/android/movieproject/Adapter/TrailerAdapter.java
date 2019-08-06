package com.example.android.movieproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.movieproject.R;
import com.example.android.movieproject.Others.Trailer;

import java.util.List;

/**
 * Created by owner on 8/6/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MyHolder> {
    Context context;
    List<Trailer> trailerList;

    public TrailerAdapter(Context context, List<Trailer> trailerList) {
        this.context = context;
        this.trailerList = trailerList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_trailer, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.trailername.setText(trailerList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView trailername;

        public MyHolder(View itemView) {
            super(itemView);
            trailername = (TextView)itemView.findViewById(R.id.name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    String id_trailer = trailerList.get(pos).getKey();
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id_trailer));
                    context.startActivity(i);
                }
            });
        }
    }
}
