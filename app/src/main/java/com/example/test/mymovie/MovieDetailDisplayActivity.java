package com.example.test.mymovie;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MovieDetailDisplayActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView up_button;
    ImageView down_button;
    TextView up_number;
    TextView down_number;
    Button movie_review_write_button;
    Button view_all_button;
    ListView movie_review_listview;
    MovieReviewAdapter adapter;

    int number= 0;

    Boolean up_button_selected = false;
    Boolean down_button_selected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_display);

        TextView synopsis = (TextView)findViewById(R.id.movie_synopsis);
        synopsis.setText(readTxt());

        up_button = (ImageView)findViewById(R.id.up_button);
        down_button = (ImageView)findViewById(R.id.down_button);
        up_button.setOnClickListener(this);
        down_button.setOnClickListener(this);

        movie_review_write_button = (Button)findViewById(R.id.movie_review_write_button);
        view_all_button = (Button)findViewById(R.id.view_all);
        movie_review_write_button.setOnClickListener(this);
        view_all_button.setOnClickListener(this);

        up_number = (TextView)findViewById(R.id.up_number);
        down_number = (TextView)findViewById(R.id.down_number);

        movie_review_listview = (ListView)findViewById(R.id.movie_review_listview);
        adapter = new MovieReviewAdapter();
        adapter.additem(5, "절대 잊을 수 없는 인생 최고의 영화였습니다.");
        adapter.additem(4.5f, "이 영화 너무 재미있습니다.");
        movie_review_listview.setAdapter(adapter);
    }


    private String readTxt() {
        String data = null;
        InputStream inputStream = getResources().openRawResource(R.raw.synopsis);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }

            data = new String(byteArrayOutputStream.toByteArray(),"MS949");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void up_button() {
        if(up_button_selected == false) {
            up_button.setImageResource(R.drawable.ic_thumb_up_selected);
            up_button_selected = true;

            number = Integer.parseInt(up_number.getText().toString());
            number++;
            up_number.setText(Integer.toString(number));
        } else {
            up_button.setImageResource(R.drawable.ic_thumb_up);
            up_button_selected = false;

            number = Integer.parseInt(up_number.getText().toString());
            number--;
            up_number.setText(Integer.toString(number));
        }
    }

    public void down_button() {
        if(down_button_selected == false) {
            down_button.setImageResource(R.drawable.ic_thumb_down_selected);
            down_button_selected = true;

            number = Integer.parseInt(down_number.getText().toString());
            number++;
            down_number.setText(Integer.toString(number));
        } else {
            down_button.setImageResource(R.drawable.ic_thumb_down);
            down_button_selected = false;

            number = Integer.parseInt(down_number.getText().toString());
            number--;
            down_number.setText(Integer.toString(number));
        }
    }

    @Override
    public void onClick(View v) {
        if(v == up_button) {
            if(down_button_selected == true) {
                down_button.setImageResource(R.drawable.ic_thumb_down);
                down_button_selected = false;

                number = Integer.parseInt(down_number.getText().toString());
                number--;
                down_number.setText(Integer.toString(number));
            }
            up_button();
        } else if(v == down_button) {
            if(up_button_selected == true) {
                up_button.setImageResource(R.drawable.ic_thumb_up);
                up_button_selected = false;

                number = Integer.parseInt(up_number.getText().toString());
                number--;
                up_number.setText(Integer.toString(number));
            }
            down_button();
        } else if (v == movie_review_write_button) {
            Intent intent = new Intent(this, MovieReviewWriteActivity.class);
            startActivityForResult(intent, 1000);
        } else if(v == view_all_button) {
            Intent intent = new Intent(this, ViewAllActivity.class);

            ArrayList<MovieReviewListViewItem> items = new ArrayList<MovieReviewListViewItem>();
            for(int i = 0; i < adapter.getCount(); i++) {
                items.add(adapter.getItem(i));
            }

            intent.putExtra("items", items);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1000) {
            adapter.additem(data.getExtras().getFloat("rating"), data.getExtras().getString("movie_review"));
            movie_review_listview.setAdapter(adapter);
        }
    }

}
