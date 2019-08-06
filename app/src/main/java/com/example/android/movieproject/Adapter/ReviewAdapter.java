package com.example.android.movieproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.movieproject.Activity.DetailActivity;
import com.example.android.movieproject.Others.Review;
import com.example.android.movieproject.Others.Trailer;
import com.example.android.movieproject.R;

import java.util.List;

/**
 * Created by owner on 8/8/2017.
 */

public class ReviewAdapter {
    Context context;
    List<Review> reviewList;

    public ReviewAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;

    }
}