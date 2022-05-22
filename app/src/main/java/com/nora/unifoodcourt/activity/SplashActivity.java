package com.nora.unifoodcourt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.nora.unifoodcourt.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private Context mContext;
    private ActivitySplashBinding mBinding;
    private final int SPLASH_TIME = 3; //Seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mContext = SplashActivity.this;

        new Handler().postDelayed(() -> {
            startActivity(new Intent(mContext, SignInActivity.class));
            finish();
        }, SPLASH_TIME * 1000);

    }


}