package com.example.rahul.srmhackandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ResultDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);

        String path = getIntent().getExtras().getString("path");
        String text = getIntent().getExtras().getString("text");

        ImageView imageView = findViewById(R.id.scanned_image);
        TextView textView = findViewById(R.id.result_text);

        Picasso.get().load(path).into(imageView);
        textView.setText(text);
        textView.setTextIsSelectable(true);
    }
}
