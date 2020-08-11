package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.skyheight.R;
import com.android.skyheight.adaptor.SiteGridAdaptor;
import com.android.skyheight.model.SiteGridList;

import java.util.ArrayList;
import java.util.List;

public class SiteListView extends AppCompatActivity {
RecyclerView recyclerView;
List<SiteGridList> siteGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_list_view);
        recyclerView = findViewById(R.id.recycle_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Site List Status");
        siteGrid = new ArrayList<>();
        siteGrid.add(new SiteGridList(true,"10 lac","indore",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(false,"20 lac","sehore",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(true,"30 lac","bhopal",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(false,"9 lac","khandwa",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(true,"8 lac","vijaynagar",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(false,"15 lac","bengali",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(true,"17 lac","palasiya",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(true,"18 lac","gitabhawan",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(false,"20 lac","LIG",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(false,"10 lac","rajwada",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(true,"23 lac","khajrana",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(false,"10 lac","malavi nagar",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(true,"14 lac","musakhedi",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(true,"16 lac","teen imali",String.valueOf(R.drawable.is_not_book),"bokk"));
        siteGrid.add(new SiteGridList(true,"17 lac","indore",String.valueOf(R.drawable.is_not_book),"book"));
        siteGrid.add(new SiteGridList(false,"19 lac","indore",String.valueOf(R.drawable.is_not_book),"book"));


        SiteGridAdaptor siteGridAdaptor = new SiteGridAdaptor(siteGrid,SiteListView.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
         recyclerView.setAdapter(siteGridAdaptor);

    }
}