package com.example.test.mymovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class MovieReviewWriteActivity extends AppCompatActivity implements View.OnClickListener {

    Button movie_review_save_button;
    Button movie_review_cancel_button;
    RatingBar ratingbar;
    EditText movie_review_edittext;
    float rating;
    String movie_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_review_write);

        movie_review_save_button = (Button)findViewById(R.id.movie_review_save_button);
        movie_review_cancel_button = (Button)findViewById(R.id.movie_review_cancel_button);
        movie_review_save_button.setOnClickListener(this);
        movie_review_cancel_button.setOnClickListener(this);

        ratingbar = (RatingBar)findViewById(R.id.ratingbar);
        movie_review_edittext = (EditText)findViewById(R.id.movie_review_edittext);
    }

    @Override
    public void onClick(View v) {
        if(v == movie_review_save_button) {
            rating = ratingbar.getRating();
            movie_review = movie_review_edittext.getText().toString();

            Intent intent = new Intent();
            intent.putExtra("rating", rating);
            intent.putExtra("movie_review", movie_review);
            setResult(RESULT_OK, intent);
            finish();
        } else if(v == movie_review_cancel_button) {
            finish();
        }
    }
}
