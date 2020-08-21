package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.skyheight.R;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    Prefrence youprefrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        youprefrence=Prefrence.getInstance(this);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (youprefrence.getData(ConstantClass.TOKEN).length()>10){
                    Intent intent=new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent=new Intent(SplashActivity.this,UserLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);


    }
}