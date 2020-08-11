package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.adaptor.UserAdaptor;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.UserList;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity {
RecyclerView recyclerView;
RelativeLayout relativeLayout;
UserAdaptor userAdaptor;
ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("User List");
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view2);
        relativeLayout=findViewById(R.id.relative_layout);
        progressBar = findViewById(R.id.progressbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        userlist();
    }

    private void userlist() {
        Call<ArrayList<UserList>> userResponseCall = ApiClient.getUserService().allusers();
        userResponseCall.enqueue(new Callback<ArrayList<UserList>>() {
            @Override
            public void onResponse(Call<ArrayList<UserList>> call, Response<ArrayList<UserList>> response) {

                //Type listType = new TypeToken<ArrayList<UserList>>() {}.getType();
                //ArrayList<UserList> allUserses = new GsonBuilder().create().fromJson(String.valueOf(response.body()), listType);
                //Log.d("Bhagawam", "Responce: " + allUserses.size());
                if (response.code()==200){
                    progressBar.setVisibility(View.GONE);
                    ArrayList<UserList> userlist = response.body();
                    userAdaptor = new UserAdaptor(CustomerListActivity.this,response.body(),userlist);
                    recyclerView.setAdapter(userAdaptor);

                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<UserList>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
