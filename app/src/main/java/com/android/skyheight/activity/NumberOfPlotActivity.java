package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.PlotsModel;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;
import com.android.skyheight.utils.SiteUtils;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NumberOfPlotActivity extends AppCompatActivity {
TextInputLayout plot_number;
Prefrence yourprefrence;
String token ="";
String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_plot);
        plot_number= findViewById(R.id.plot_number);
        yourprefrence=Prefrence.getInstance(this);
    }

    public void plot(View view) {
        String plot =plot_number.getEditText().getText().toString();
        //PlotsModel plotsModel = new PlotsModel(plot,yourprefrence.getData(SiteUtils.ID));
        plotCount(plot);


    }

    private void plotCount(String plot_count) {
        Call<PlotsModel> userResponse = ApiClient.getUserService().
                plots("Bearer "+yourprefrence.getData(ConstantClass.TOKEN)
                ,yourprefrence.getData(SiteUtils.ID),plot_count);
        userResponse.enqueue(new Callback<PlotsModel>() {
            @Override
            public void onResponse(Call<PlotsModel> call, Response<PlotsModel> response) {
                if (response.code()==201){
                    Toast.makeText(getApplicationContext(),"Plot "+response.body().getNo_plots(),Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlotsModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}