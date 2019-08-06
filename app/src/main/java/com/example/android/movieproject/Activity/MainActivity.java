package com.example.android.movieproject.Activity;

import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.movieproject.Api.ApiClient;
import com.example.android.movieproject.Api.ApiInterface;
import com.example.android.movieproject.Adapter.CustomAdapter;
import com.example.android.movieproject.Others.Movie;
import com.example.android.movieproject.R;
import com.example.android.movieproject.Others.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String SAVED_LAYOUT_MANAGER = "recycler_state";
    //TODO Create icon

    CustomAdapter adapter;
    RecyclerView mrecyclerview;
    List<Result> resultList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_popular){
            new RequestMovie().execute("popular");
        } else if(item.getItemId() == R.id.action_top_rated){
            new RequestMovie().execute("top_rated");
        } else if(item.getItemId() == R.id.action_upcoming){
            new RequestMovie().execute("upcoming");
        }else if(item.getItemId() == R.id.action_now_playing){
            new RequestMovie().execute("now_playing");
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecyclerview = (RecyclerView)findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CustomAdapter(MainActivity.this, resultList);
        mrecyclerview.setAdapter(adapter);
        mrecyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        new RequestMovie().execute("popular");
    }


    private class RequestMovie extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params) {
            String kategori = params[0];
            if(kategori.equals("popular")){
                ApiInterface apiInterface = ApiClient.getRetrofit()
                        .create(ApiInterface.class);
                Call<Movie> call = apiInterface.getPopular();
                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Movie movie = response.body();
                        adapter.setData(movie.getResults());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });
            }else if(kategori.equals("top_rated")){
                    ApiInterface apiInterface = ApiClient.getRetrofit()
                            .create(ApiInterface.class);
                    Call<Movie> call = apiInterface.getTopRated();
                    call.enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                            Movie movie = response.body();
                            adapter.setData(movie.getResults());
                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {

                        }
                    });
            }else if(kategori.equals("upcoming")){
                ApiInterface apiInterface = ApiClient.getRetrofit()
                        .create(ApiInterface.class);
                Call<Movie> call = apiInterface.getUpcoming();
                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Movie movie = response.body();
                        adapter.setData(movie.getResults());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });
            }else if(kategori.equals("now_playing")){
                ApiInterface apiInterface = ApiClient.getRetrofit()
                        .create(ApiInterface.class);
                Call<Movie> call = apiInterface.getNowPlaying();
                call.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        Movie movie = response.body();
                        adapter.setData(movie.getResults());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });
            }
            return null;
        }
    }
}
