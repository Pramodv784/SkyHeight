package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;
import fr.ganfra.materialspinner.MaterialSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.skyheight.R;

public class AddSiteActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener{
    Spinner site_list;
           MaterialSpinner site_type,land_type,site_location,site_unit,area_type,monthly;
    String[] list = {"Sell","Rent/lease"};
    String[] type = {"Residential","Commercial"};
    String[] type_land = {"Agriculture/Farm land ","Insdustrial land ","Commercial land"};
    String[] location_land = {"indore","bhopal","sehore","khandwa"};
    String[] area = {"acres","Hectare","Bigha","Kattha","Square Feet","Square Inch"};
    String[] month = {"Quarterly","Half yearly","annually"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        site_list=findViewById(R.id.list_spinner);


       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
 site_list.setOnItemSelectedListener(this);
        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        site_list.setAdapter(a);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}