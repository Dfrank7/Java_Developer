package com.example.dfrank.java_developer.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dfrank.java_developer.R;

public class Detail_Activity extends AppCompatActivity {
    private TextView gitLink, username;
    Toolbar mActionBar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        imageView = (ImageView) findViewById(R.id.image);
        gitLink = (TextView) findViewById(R.id.github);
        username = (TextView) findViewById(R.id.name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String name = getIntent().getExtras().getString("login");
        String avatar = getIntent().getExtras().getString("avatar");
        String link = getIntent().getExtras().getString("url");

        gitLink.setText(link);
        Linkify.addLinks(gitLink, Linkify.WEB_URLS);
        username.setText(name);
        Glide.with(this).load(avatar).into(imageView);
    }
}
