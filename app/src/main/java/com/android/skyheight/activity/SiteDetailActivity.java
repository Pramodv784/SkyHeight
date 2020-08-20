package com.android.skyheight.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.model.SiteListModel;

import java.util.ArrayList;
import java.util.List;

public class SiteDetailActivity extends AppCompatActivity {
    TextView owner1, area, price, location,llocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_detail);
        owner1 = findViewById(R.id.owner1);
        area = findViewById(R.id.pplot_area);
        price = findViewById(R.id.price);
        location = findViewById(R.id.location1);
        llocation = findViewById(R.id.llocation);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        SiteListModel siteListModel = (SiteListModel) args.getSerializable("ARRAYLIST");
        owner1.setText(siteListModel.getOwner().getUsername());
        price.setText("₹  " + siteListModel.getPrice() + " sq/ft");
        if (siteListModel.getSite_location()!=null){
            location.setText(siteListModel.site_location.getAddress());
            llocation.setText(siteListModel.site_location.getAddress());
        }
        else {
            location.setText("yiutut");
            llocation.setText("dfds");
        }



    }

    public void call(View view) {
        callPhoneNumber();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber();
            }
        }
    }

    private void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(SiteDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 101);
                    return;
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:  8359968242"));
                startActivity(callIntent);

            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:  8359968242"));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
