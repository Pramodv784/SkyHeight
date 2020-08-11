package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.skyheight.R;

public class DeleteCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_customer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}