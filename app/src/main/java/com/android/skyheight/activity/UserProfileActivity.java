package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.android.skyheight.R;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;

public class UserProfileActivity extends AppCompatActivity {
TextView user_name,user_mobile,user_address;
Prefrence yourprefrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        yourprefrence= Prefrence.getInstance(UserProfileActivity.this);
        user_name =findViewById(R.id.user1);
        user_mobile =findViewById(R.id.mobile1);
        user_address =findViewById(R.id.address1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        yourprefrence = Prefrence.getInstance(UserProfileActivity.this);
        user_name.setText(yourprefrence.getData(ConstantClass.USERNAME));
        user_mobile.setText(yourprefrence.getData(ConstantClass.MOBILE_NUMBER));
        user_address.setText(yourprefrence.getData(ConstantClass.ADDRESS));

    }
}