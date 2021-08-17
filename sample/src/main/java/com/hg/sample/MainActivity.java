package com.hg.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onJavaClick(View view) {
        startActivity(new Intent(this, JavaActivity.class));
    }

    public void onKotlinClick(View view) {
        startActivity(new Intent(this, KotlinActivity.class));
    }
}