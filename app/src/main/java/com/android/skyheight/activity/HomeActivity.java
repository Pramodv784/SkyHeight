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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.skyheight.R;
import com.android.skyheight.adaptor.SiteListAdaptor;
import com.android.skyheight.model.SiteListModel;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.jackandphantom.circularimageview.CircleImage;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    NavigationView navigationView;
    CircleImage circularImageView;
    RecyclerView recyclerView;
    ToggleButton toggleButton;
    Prefrence yourprefrence;
    TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        yourprefrence = Prefrence.getInstance(HomeActivity.this);
        recyclerView = findViewById(R.id.recycle);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        View headerview = navigationView.getHeaderView(0);
        circularImageView = (CircleImage) headerview.findViewById(R.id.user_image);
        userName =headerview.findViewById(R.id.userName);
        userName.setText(yourprefrence.getData(ConstantClass.USERNAME));
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) HomeActivity.this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        SiteListModel[] lis = new SiteListModel[]{
                new SiteListModel(String.valueOf(R.drawable.plotimage2), 	5000 , "78 sector indore MP", "12 acres",
                        "ramesh sing ","0"),
                new SiteListModel(String.valueOf(R.drawable.plotimage3), 7000, "78 sector indore MP", "12 acres",
                        "ram verma ","1"),
                new SiteListModel(String.valueOf(R.drawable.plotimage2), 10000, "78 sector indore MP", "12 acres",
                        "anil sing sing ","2"),
                new SiteListModel(String.valueOf(R.drawable.plotimage3), 6000, "78 sector indore MP", "12 acres",
                        "devraj verma ","3"),
                new SiteListModel(String.valueOf(R.drawable.plotimage2), 6500, "78 sector indore MP", "12 acres",
                        "mahesh  ","4"),
                new SiteListModel(String.valueOf(R.drawable.plotimage3), 8000, "78 sector indore MP", "12 acres",
                        "mukesh ","4"),
                new SiteListModel(String.valueOf(R.drawable.plotimage3), 8000, "78 sector indore MP", "12 acres",
                        "M ","4"),
                new SiteListModel(String.valueOf(R.drawable.plotimage3), 8000, "78 sector indore MP", "12 acres",
                        "mukesh ","4"),
                new SiteListModel(String.valueOf(R.drawable.plotimage3), 8000, "78 sector indore MP", "12 acres",
                        "mukesh ","4")
        };
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        SiteListAdaptor adaptor = new SiteListAdaptor(lis,HomeActivity.this);
        recyclerView.setAdapter(adaptor);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.admin_control:
               // checkuser();
                startActivity(new Intent(HomeActivity.this,AdminViewActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(HomeActivity.this,UserProfileActivity.class));
                break;
            case R.id.logout:
                logout();
                break;
            case R.id.share:
                share();
                break;
            case R.id.language:
                Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.history:
                Toast.makeText(getApplicationContext(),"Feature In Progress... ",Toast.LENGTH_SHORT).show();

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
        if (yourprefrence.getData(ConstantClass.TYPE).equals("Super_Admin")){


        }
        else {
            //Snackbar.make(drawer, "You Don't have Access of Admin Panel", Snackbar.LENGTH_LONG);
            Toast.makeText(getApplicationContext(),"You Don't have Access of Admin Panel",Toast.LENGTH_SHORT).show();
        }
    }

    private void logout() {
        yourprefrence.clear();
        startActivity(new Intent(HomeActivity.this,UserLoginActivity.class));
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
            case R.id.fav:
                startActivity(new Intent(HomeActivity.this, FavoriteList.class));
        }
        return  super.onOptionsItemSelected(item);
    }
}
