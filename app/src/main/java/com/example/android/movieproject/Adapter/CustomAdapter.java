package com.example.android.movieproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movieproject.Activity.DetailActivity;
import com.example.android.movieproject.Api.ApiInterface;
import com.example.android.movieproject.R;
import com.example.android.movieproject.Others.Result;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by owner on 8/5/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    List<Result> resultList;
    Context context;

    public CustomAdapter( Context context, List<Result> resultList) {
        this.resultList = resultList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater
                .inflate(R.layout.list_movie_poster, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext())
                .load(ApiInterface.BASE_IMG + resultList.get(position).getPosterPath())
                .centerCrop()
                .into(holder.Poster);

        holder.Title.setText(resultList.get(position).getOriginalTitle());
        holder.Vote.setText(Double.toString(resultList.get(position).getVoteAverage()));

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Result data = resultList.get(position);
                Intent i = new Intent(holder.itemView.getContext(), DetailActivity.class);
                i.putExtra("movie", new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setData(List<Result> resultList){
        this.resultList = resultList;
        notifyDataSetChanged();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView Poster;
        TextView Title, Vote;

        public MyViewHolder(View itemView){
            super(itemView);
            Poster = (ImageView)itemView.findViewById(R.id.poster);
            Title = (TextView) itemView.findViewById(R.id.title);
            Vote = (TextView) itemView.findViewById(R.id.vote);
        }
    }

}
