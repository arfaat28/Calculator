package com.example.mycalci;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=5000;

    Animation topAnimation, bottomAnimation, midAnimation;
    View first,second,third,fourth,fifth,sixth;
    TextView calci,tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        midAnimation = AnimationUtils.loadAnimation(this, R.anim.mid_animation);


        first = findViewById(R.id.firstline);
        second = findViewById(R.id.secondline);
        third = findViewById(R.id.thirdline);
        fourth = findViewById(R.id.fourthline);
        fifth = findViewById(R.id.fifthline);
        sixth = findViewById(R.id.sixthline);

        calci = findViewById(R.id.calci);
        tagline = findViewById(R.id.tagline);

        first.setAnimation(topAnimation);
        second.setAnimation(bottomAnimation);
        third.setAnimation(topAnimation);
        fourth.setAnimation(midAnimation);
        fifth.setAnimation(bottomAnimation);
        sixth.setAnimation(midAnimation);

        calci.setAnimation(topAnimation);
        tagline.setAnimation(midAnimation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SeconActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}