package com.example.test.mymovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MovieReviewAdapter extends BaseAdapter {

    private ArrayList<MovieReviewListViewItem> items = new ArrayList<MovieReviewListViewItem>();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MovieReviewListViewItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_movie_review_list_view_item, parent, false);
        }

        RatingBar ratingbar = (RatingBar)convertView.findViewById(R.id.ratingbar);
        TextView textView_movie_review_contents = (TextView) convertView.findViewById(R.id.movie_review_contents);

        MovieReviewListViewItem item = getItem(position);

        ratingbar.setRating(item.getRating());
        textView_movie_review_contents.setText(item.getMovie_review_contents());

        return convertView;
    }

    public void additem(float rating, String movie_review_contents) {
        MovieReviewListViewItem item = new MovieReviewListViewItem();

        item.setRating(rating);
        item.setMovie_review_contents(movie_review_contents);

        items.add(item);
    }
}
