package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.android.skyheight.R;
import com.android.skyheight.utils.ProgressButton;
import com.google.android.material.snackbar.Snackbar;
public class AdminActivity extends AppCompatActivity {
    EditText id,password;
    LinearLayout linear;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        id=findViewById(R.id.id);
        password =findViewById(R.id.password);
        linear =findViewById(R.id.linear);
        view =findViewById(R.id.myprogressbutton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressButton progressButton = new ProgressButton(v, AdminActivity.this);
                progressButton.ButtonActivated();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressButton.ButtonFinished();
                        startActivity(new Intent(AdminActivity.this,AdminViewActivity.class));
                    }
                },1000);
            }
        });
    }
    public void admin(View view) {
        if(id.getText().toString().isEmpty()){
            id.setError("Enter Id ");
            id.requestFocus();
        }
        else if(password.getText().toString().isEmpty()){
            password.setError("Enter Password");
            password.requestFocus();
        }
        else if (id.getText().toString().equals("admin") && password.getText().toString().equals("12345")){
            startActivity(new Intent(AdminActivity.this,AdminViewActivity.class));
        }
        else {
            Snackbar snackbar = Snackbar
                    .make(linear, "Wrong Id or password", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
