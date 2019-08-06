package com.example.android.movieproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.android.movieproject.Adapter.ReviewAdapter;
import com.example.android.movieproject.Api.ApiClient;
import com.example.android.movieproject.Api.ApiInterface;
import com.example.android.movieproject.Others.Result;
import com.example.android.movieproject.Others.Review;
import com.example.android.movieproject.R;
import com.example.android.movieproject.Others.Trailer;
import com.example.android.movieproject.Adapter.TrailerAdapter;
import com.example.android.movieproject.Others.TrailerResult;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    TrailerAdapter adapter;
    List<Trailer> trailerList;
    ReviewAdapter reviewAdapter;
    List<Review> reviewList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    int id_movie;
    ImageView backdrop, poster;
    TextView tgl, overview, voting, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        poster = (ImageView)findViewById(R.id.poster);
        backdrop = (ImageView)findViewById(R.id.backdrop);
        tgl = (TextView)findViewById(R.id.tanggal);
        voting = (TextView)findViewById(R.id.vote);
        overview = (TextView)findViewById(R.id.overview);
        title = (TextView)findViewById(R.id.Title);
        recyclerView = (RecyclerView)findViewById(R.id.trailerview);

        Result data = new GsonBuilder().
                create().
                fromJson(DetailActivity.this.getIntent().getStringExtra("movie"), Result.class);

        Glide.with(this).load(ApiInterface.BASE_IMG + data.getPosterPath()).into(poster);
        Glide.with(this).load(ApiInterface.BACKDROP_IMG + data.getBackdropPath()).into(backdrop);

        String date = data.getReleaseDate();
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("MMM dd, yyyy");
        try{
            Date oneMay = input.parse(date);
            tgl.setText("Release Date : \n" + output.format(oneMay));
        }catch (Exception e){
            e.printStackTrace();
        }


        voting.setText("Vote Average : \n" + data.getVoteAverage());
        overview.setText("Overview : \n" + data.getOverview() + "\n");
        title.setText(data.getOriginalTitle());
        id_movie = data.getId();



        reviewList = new ArrayList<>();
        trailerList = new ArrayList<>();

        reviewAdapter = new ReviewAdapter(this, reviewList);
        adapter = new TrailerAdapter(this, trailerList);

        getSupportActionBar().setTitle(data.getOriginalTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(DetailActivity.this, 1));
        recyclerView.setAdapter(adapter);
        loadJSON();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void loadJSON() {
        ApiInterface apiInterface = ApiClient.getRetrofit()
                .create(ApiInterface.class);
        Call<TrailerResult> call = apiInterface.getTrailerMovie(id_movie);
        call.enqueue(new Callback<TrailerResult>() {
            @Override
            public void onResponse(Call<TrailerResult> call, Response<TrailerResult> response) {
                List<Trailer> trailers = response.body().getResults();
                recyclerView.setAdapter(new TrailerAdapter(DetailActivity.this, trailers));
                recyclerView.smoothScrollBy(1,2);
            }

            @Override
            public void onFailure(Call<TrailerResult> call, Throwable t) {

            }
        });
    }
}
