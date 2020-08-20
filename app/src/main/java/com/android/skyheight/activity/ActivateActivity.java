package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.skyheight.R;
import com.android.skyheight.model.UserList;

public class ActivateActivity extends AppCompatActivity {
TextView username,mobile,address,type,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
        username=findViewById(R.id.username);
        mobile=findViewById(R.id.mobile_number);
        address=findViewById(R.id.address);
        status=findViewById(R.id.user_status);
        type=findViewById(R.id.user_type);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        UserList userList = (UserList) args.getSerializable("ARRAYLIST");
        username.setText(userList.getUser_name());
        mobile.setText(userList.getMobile());
        address.setText(userList.getAddress());
        type.setText(userList.getType());
        if (userList.getIs_active()==true){
            status.setText("Active");
        }
        else {
            status.setText("Deactive");
        }
    }
}