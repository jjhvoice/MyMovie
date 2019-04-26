package com.example.test.mymovie;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity implements View.OnClickListener{

    Button movie_review_write_button;
    ListView movie_review_listview;
    MovieReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        movie_review_write_button = (Button)findViewById(R.id.movie_review_write_button);
        movie_review_write_button.setOnClickListener(this);

        movie_review_listview = (ListView)findViewById(R.id.movie_review_listview);
        adapter = new MovieReviewAdapter();
        ArrayList<MovieReviewListViewItem> items = (ArrayList<MovieReviewListViewItem>)getIntent().getSerializableExtra("items");
        for(int i = 0; i < items.size(); i++) {
            adapter.additem(items.get(i).getRating(), items.get(i).getMovie_review_contents());
        }
        movie_review_listview.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == movie_review_write_button) {
            Intent intent = new Intent(this, MovieReviewWriteActivity.class);
            startActivityForResult(intent, 2000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 2000) {
            adapter.additem(data.getExtras().getFloat("rating"), data.getExtras().getString("movie_review"));
            movie_review_listview.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MovieDetailDisplayActivity.class);

        ArrayList<MovieReviewListViewItem> items = new ArrayList<MovieReviewListViewItem>();
        for(int i = 0; i < adapter.getCount(); i++) {
            items.add(adapter.getItem(i));
        }

        intent.putExtra("items", items);
        startActivity(intent);
    }
}
