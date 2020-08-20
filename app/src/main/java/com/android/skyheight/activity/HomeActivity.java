package com.android.skyheight.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.skyheight.R;
import com.android.skyheight.adaptor.SiteListAdaptor;
import com.android.skyheight.adaptor.UserAdaptor;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.SiteListModel;
import com.android.skyheight.model.SiteModel;
import com.android.skyheight.model.UserList;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jackandphantom.circularimageview.CircleImage;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;
    CircleImage circularImageView;
    RecyclerView recyclerView;
    ToggleButton toggleButton;
    Prefrence yourprefrence;
    TextView userName;
    Button loginbtn,signup;
    SiteListAdaptor siteListAdaptor;
    ProgressBar progressBar;
    ArrayList<SiteListModel> sitelist = new ArrayList<SiteListModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        progressBar=findViewById(R.id.progressbar);
        yourprefrence = Prefrence.getInstance(HomeActivity.this);
        recyclerView = findViewById(R.id.recycle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerview = navigationView.getHeaderView(0);
        circularImageView = (CircleImage) headerview.findViewById(R.id.user_image);
        userName = headerview.findViewById(R.id.userName);
        loginbtn=headerview.findViewById(R.id.loginbtn);
        signup=headerview.findViewById(R.id.signup);
        if (yourprefrence.getData(ConstantClass.TOKEN).length() > 6) {
            userName.setText(yourprefrence.getData(ConstantClass.USERNAME));
            loginbtn.setVisibility(View.GONE);
            signup.setVisibility(View.GONE);
        } else {
            userName.setText("Guest User");
            loginbtn.setVisibility(View.VISIBLE);
            signup.setVisibility(View.VISIBLE);
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,UserLoginActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SignupActivity.class));
            }
        });
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) HomeActivity.this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        siteList();
    }

    private void siteList() {

        Call<ArrayList<SiteListModel>> userResponseCall = ApiClient.getUserService()
                .sitelist("Bearer "+yourprefrence.getData(ConstantClass.TOKEN));
        userResponseCall.enqueue(new Callback<ArrayList<SiteListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SiteListModel>> call, Response<ArrayList<SiteListModel>> response) {
                if (response.code()==200){
                    if (!response.body().isEmpty()){
                        progressBar.setVisibility(View.GONE);
                    Log.i( "onResponse: ",response.body().toString() );
                    sitelist=response.body();
                    siteListAdaptor = new SiteListAdaptor(HomeActivity.this,response.body(),sitelist);
                    recyclerView.setAdapter(siteListAdaptor);
                    recyclerView.setAdapter(siteListAdaptor);}
                    else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Empty Site List ",Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"List Failed ",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SiteListModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e( "onResponse: ","failed"+t);
                Toast.makeText(getApplicationContext(),"Something went wrong ",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.admin_control:
                // checkuser();
                startActivity(new Intent(HomeActivity.this, AdminViewActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(HomeActivity.this, UserProfileActivity.class));
                break;
            case R.id.logout:
                logout();
                break;
            case R.id.share:
                share();
                break;
            case R.id.language:
                Toast.makeText(getApplicationContext(), "Feature In Progress... ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.history:
                Toast.makeText(getApplicationContext(), "Feature In Progress... ", Toast.LENGTH_SHORT).show();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void share() {
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share App link ");
        String app_url = " https://play.google.com/store";
        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, app_url);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
    private void checkuser() {
        if (yourprefrence.getData(ConstantClass.TYPE).equals("Super_Admin")) {

        } else {
            //Snackbar.make(drawer, "You Don't have Access of Admin Panel", Snackbar.LENGTH_LONG);
            Toast.makeText(getApplicationContext(), "You Don't have Access of Admin Panel", Toast.LENGTH_SHORT).show();
        }
    }

    private void logout() {
        yourprefrence.clear();
        Toast.makeText(getApplicationContext(), "Mobile is " + yourprefrence.getData(ConstantClass.MOBILE_NUMBER), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(HomeActivity.this, UserLoginActivity.class));
        // Toast.makeText(getApplicationContext(),"Email"+yourprefrence.getData(ConstantClass.EMAIL),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Snackbar.make(drawer, "Are you Sure want to exit", Snackbar.LENGTH_LONG)
                .setAction(R.string.close, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishAffinity();
                    }
                }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {

        }
        return super.onOptionsItemSelected(item);
    }
}
