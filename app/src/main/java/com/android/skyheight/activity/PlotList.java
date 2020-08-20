package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.adaptor.PlotListAdaptor;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.PlotListModel;
import com.android.skyheight.model.UserList;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlotList extends AppCompatActivity {
RecyclerView recyclerView;
List<PlotListModel> siteGrid;
    Prefrence yourprefrence;
    ProgressBar progressBar;
    ArrayList<PlotListModel> plotList = new ArrayList<PlotListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_list);
        recyclerView = findViewById(R.id.recycle_view);
        yourprefrence=Prefrence.getInstance(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar= findViewById(R.id.progressbar);
        getSupportActionBar().setTitle("Site List Status");

      plotlist();
    }

    private void plotlist() {
        final Call<ArrayList<PlotListModel>> userResponse = ApiClient.getUserService().allplot("Bearer "
                +yourprefrence.getData(ConstantClass.TOKEN));
        userResponse.enqueue(new Callback<ArrayList<PlotListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PlotListModel>> call, Response<ArrayList<PlotListModel>> response) {
                if (response.code()==200){
                    if (!response.body().isEmpty()){
                    progressBar.setVisibility(View.GONE);

                    plotList=response.body();
                    PlotListAdaptor plotListAdaptor = new PlotListAdaptor(PlotList.this,response.body(),plotList);
                    recyclerView.setLayoutManager(new GridLayoutManager(PlotList.this,4));
                    recyclerView.setAdapter(plotListAdaptor);}
                    else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Empty List",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PlotListModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}