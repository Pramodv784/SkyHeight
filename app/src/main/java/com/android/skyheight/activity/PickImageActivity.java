package com.android.skyheight.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.skyheight.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class PickImageActivity extends AppCompatActivity {
Button pick_image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);
        pick_image =findViewById(R.id.pick_image);
        pick_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(PickImageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                    ActivityCompat.requestPermissions(PickImageActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK){
            final ImageView image =findViewById(R.id.image);
            final List<Bitmap> bitmap = new ArrayList<>();
            final ClipData clipData =data.getClipData();
            if (clipData!=null){
                for (int i=0;i<clipData.getItemCount();i++)
                {
                    Uri imageuri =clipData.getItemAt(i).getUri();
                    try {
                        InputStream is = getContentResolver().openInputStream(imageuri);

                        Bitmap bitmap1 = BitmapFactory.decodeStream(is);
                        bitmap.add(bitmap1);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }

            }
            else {
                Uri imageuri =data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imageuri);
                    Bitmap bitmap1 = BitmapFactory.decodeStream(is);
                    bitmap.add(bitmap1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
              new Thread(new Runnable() {
                  @Override
                  public void run() {
                   for (final Bitmap b :bitmap){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            image.setImageBitmap(b);
                        }
                    });
                       try {
                           Thread.sleep(3000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                  }
              }).start();
        }
    }
}