package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.skyheight.R;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.UserDetail;
import com.android.skyheight.model.UserList;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlotActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
TextInputLayout plot_number,plot_size,plot_description;
Spinner spinner;
    Prefrence yourprefrence;
    String type1="Broker";
    ArrayList<UserDetail> brokerlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plot);
        plot_number = findViewById(R.id.plot_number);
        plot_size = findViewById(R.id.plot_size);
        yourprefrence=Prefrence.getInstance(this);
        plot_description = findViewById(R.id.description);
        spinner= findViewById(R.id.spinner);
        brokerlist(type1);
    }

    private void brokerlist(String type) {
        Call<ArrayList<UserList>> userResponse = ApiClient.getUserService()
                .allbroker("Bearer "+yourprefrence.getData(ConstantClass.TOKEN),type);
        userResponse.enqueue(new Callback<ArrayList<UserList>>() {
            @Override
            public void onResponse(Call<ArrayList<UserList>> call, Response<ArrayList<UserList>> response) {
                if (response.code()==201){

                }
                else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserList>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addplot(View view) {

    }
}