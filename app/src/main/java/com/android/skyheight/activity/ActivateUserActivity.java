package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.adaptor.ActivateAdaptor;
import com.android.skyheight.adaptor.UserAdaptor;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.UserList;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;

import java.util.ArrayList;

public class ActivateUserActivity extends AppCompatActivity implements ActivateAdaptor.clickevents {
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ActivateAdaptor activateAdaptor;
    Prefrence yourprefrence;
    ProgressBar progressBar;
    ArrayList<UserList> userlist = new ArrayList<UserList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_user);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view3);
        relativeLayout=findViewById(R.id.relative_layout3);
        progressBar = findViewById(R.id.progressbar);
        yourprefrence = Prefrence.getInstance(ActivateUserActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        userlist();
    }
    private void userlist() {
        Call<ArrayList<UserList>> userResponseCall = ApiClient.getUserService().allusers("Bearer "+yourprefrence.getData(ConstantClass.TOKEN));
        userResponseCall.enqueue(new Callback<ArrayList<UserList>>() {
            @Override
            public void onResponse(Call<ArrayList<UserList>> call, Response<ArrayList<UserList>> response) {
                //Type listType = new TypeToken<ArrayList<UserList>>() {}.getType();
                //ArrayList<UserList> allUserses = new GsonBuilder().create().fromJson(String.valueOf(response.body()), listType);
                //Log.d("Bhagawam", "Responce: " + allUserses.size());
                if (response.code() == 200) {
                    progressBar.setVisibility(View.GONE);
                    userlist = response.body();
                    activateAdaptor = new ActivateAdaptor(ActivateUserActivity.this, response.body(), userlist);
                    recyclerView.setAdapter(activateAdaptor);
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<UserList>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onActivateClicked(UserList userList) {

    }
}