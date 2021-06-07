package com.example.digibill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class IntroductoryActivity extends AppCompatActivity {


    ImageView logo;
    LottieAnimationView lottieAnimationView;
    FloatingActionButton next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        logo = findViewById(R.id.logo);
        lottieAnimationView = findViewById(R.id.lottie);
        next_button = findViewById(R.id.next_button);

        logo.setAlpha(0f);
        lottieAnimationView.setAlpha(0f);
        next_button.setAlpha(0f);


        logo.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        lottieAnimationView.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        next_button.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });
    }
}