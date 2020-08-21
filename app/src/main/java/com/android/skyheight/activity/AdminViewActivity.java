package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.skyheight.R;

public class AdminViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Admin Control");
        getSupportActionBar().setSubtitle("Admin View");


    }

    public void addsite(View view) {
        startActivity(new Intent(AdminViewActivity.this,AddSiteActivity.class));
    }

    public void userlist(View view) {
        startActivity(new Intent(AdminViewActivity.this,CustomerListActivity.class));
    }



    public void adduser(View view) {
        startActivity(new Intent(AdminViewActivity.this,SignupActivity.class));
    }

    public void sitelist(View view) {
        startActivity(new Intent(AdminViewActivity.this, PlotList.class));
    }

    public void addpartner(View view) {
        Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();
    }

    public void removepartner(View view) {
        Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();
    }

    public void summary(View view) {
        Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();
    }

    public void expenses(View view) {
        Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();
    }

    public void activate(View view) {
        startActivity(new Intent(AdminViewActivity.this,ActivateUserActivity.class));
    }

    public void deactive(View view) {Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();
    }

    public void removesite(View view) {
        Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();
    }

    public void history(View view) {
        Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();
    }

    public void alluserlist(View view) {
        startActivity(new Intent(AdminViewActivity.this,CustomerListActivity.class));
    }

    public void sitelistplot(View view) {
        startActivity(new Intent(AdminViewActivity.this,SiteListPlotActivity.class));
    }
}
