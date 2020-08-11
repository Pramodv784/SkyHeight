package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.skyheight.R;
import com.android.skyheight.model.SiteListModel;

import java.util.ArrayList;
import java.util.List;

public class SiteDetailActivity extends AppCompatActivity {
   TextView owner,area,price,location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_detail);
        owner = findViewById(R.id.owner);
        area = findViewById(R.id.pplot_area);
        price = findViewById(R.id.price);
        location = findViewById(R.id.location1);

         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        SiteListModel siteListModel = (SiteListModel) args.getSerializable("ARRAYLIST");
        owner.setText(siteListModel.getPlot_owner());
        price.setText("₹  "+siteListModel.getPrice()+" sq/m");
        location.setText(siteListModel.getSite_address());
        area.setText(""+siteListModel.getPlot_size());

    }
}