package com.example.test.mymovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class MovieReviewListViewItem implements Serializable {

    private float rating;
    private String movie_review_contents;

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setMovie_review_contents(String movie_review_contents) {
        this.movie_review_contents = movie_review_contents;
    }

    public float getRating() {
        return rating;
    }

    public String getMovie_review_contents() {
        return movie_review_contents;
    }
}
