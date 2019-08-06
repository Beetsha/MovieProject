package com.example.android.movieproject.Others;

import com.example.android.movieproject.Others.Trailer;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by owner on 8/6/2017.
 */

public class TrailerResult {

    @SerializedName("id")
    private String id_trailer;

    @SerializedName("results")
    private List<Trailer> results;

    public String getId_trailer() {
        return id_trailer;
    }

    public List<Trailer> getResults() {
        return results;
    }
}
