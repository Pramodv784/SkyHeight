package com.android.skyheight.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fr.ganfra.materialspinner.MaterialSpinner;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.AddressModel;
import com.android.skyheight.model.SiteModel;
import com.android.skyheight.model.UserDetail;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;
import com.android.skyheight.utils.SiteUtils;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.io.File;

public class AddSiteActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    Intent myfile;
    TextView pick, file, pick_image, image;
    public static final int PICK_IMAGE = 1;
    Prefrence yourprefrence;
    String picture, anyfile, address, street, city;
    RequestBody requestFile, requestfile2;
    String name1, location,area,price,description;
    TextInputLayout site_name, site_location, site_price, site_description, site_area;
    MultipartBody.Part body1, body2;
    ProgressBar progressBar;
    String site_street,site_address,site_city;
    String[] ciy = {"Select City","Agar Malwa", "Alirajpur", "Anuppur", "Ashoknagar", "Balaghat", "Barwani", "Betul",
            "Bhind", "Bhopal", "Burhanpur", "Chhatarpur", "Chhindwara", "Damoh", "Datia", "Dewas", "Dhar", "Dindori",
            "Guna", "Gwalior", "Harda", "Hoshangabad", "Indore", "Jabalpur", "Jhabua", "Katni", "Khandwa", "Khargone",
            "Mandla", "Mandsaur", "Morena", "Narsinghpur", "Neemuch", "Panna", "Raisen", "Rajgarh", "Ratlam", "Rewa",
            "Sagar", "Satna", "Sehore", "Seoni", "Shahdol", "Shajapur", "Sheopur", "Shivpuri", "Sidhi", "Singrauli",
            "Tikamgarh", "Ujjain", "Umaria", "Vidisha"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site);
        site_name = findViewById(R.id.site_name);
        site_price = findViewById(R.id.site_price);
        site_area = findViewById(R.id.site_area);
        site_description = findViewById(R.id.description);
        pick = findViewById(R.id.pick_file);
        file = findViewById(R.id.file);
        progressBar = findViewById(R.id.progressbar);
        yourprefrence = Prefrence.getInstance(AddSiteActivity.this);
        pick_image = findViewById(R.id.pick_image);
        image = findViewById(R.id.image1);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myfile = new Intent(Intent.ACTION_GET_CONTENT);
                myfile.setType("*/*");
                startActivityForResult(myfile, 10);
            }
        });

        pick_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 50);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void addlocation(View view) {

        //startActivity(new Intent(AddSiteActivity.this, AddSiteLocationActivity.class));
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.address_layout, null);
         final EditText address = (EditText) dialogView.findViewById(R.id.site_address);
       final   EditText street = (EditText) dialogView.findViewById(R.id.site_street);
        Button add_address = (Button) dialogView.findViewById(R.id.add_address);
        final Spinner spinner =(Spinner)dialogView.findViewById(R.id.spinner);

        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_dropdown_layout,ciy);
        aa.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(aa);
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                site_address = address.getText().toString();
                site_street= street.getText().toString();
                site_city= spinner.getSelectedItem().toString().trim();
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 10:
                if (resultCode == RESULT_OK) {
                    Uri anyfile = data.getData();
                    String filename = data.getData().getPath();
                    //File f= new File(filename);
                    //anyfile = f.getName();
                    // file.setText(anyfile);
                    file.setText(filename);
                    File file1 = new File(String.valueOf(anyfile));
                    requestFile =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                    RequestBody image =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                    body1 =
                            MultipartBody.Part.createFormData("image", file1.getName(), image);
                }
                break;
            case 50:
                if (resultCode == RESULT_OK) {
                    Uri imagepath = data.getData();
                    String image_file = data.getData().getPath();
                    // File image_name =new File(image_file);
                    //picture=image_name.getName();
                    //image.setText(picture);
                    image.setText(image_file);
                    File file1 = new File(String.valueOf(imagepath));
                    requestfile2 =
                            RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                    RequestBody file =
                            RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name");
                    body2 =
                            MultipartBody.Part.createFormData("image", file1.getName(), file);
                }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        site_city = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addsite(View view) {
        name1 = site_name.getEditText().getText().toString();
         area = site_area.getEditText().getText().toString();
         price = site_price.getEditText().getText().toString();
         description = site_description.getEditText().getText().toString();
        if (name1.isEmpty()) {
            site_name.setError("Enter the Site Name");
            site_name.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            AddressModel addressModel = new AddressModel(site_address, site_street, site_city);
            //SiteModel siteModel = new SiteModel(name1, area, price, description, picture, anyfile);
            addressapi(addressModel);
        }

    }

    private void addressapi(AddressModel addressModel) {
        Call<SiteModel> userResponse = ApiClient.getUserService().addaddress("Bearer " + yourprefrence.getData(ConstantClass.TOKEN), addressModel);
        userResponse.enqueue(new Callback<SiteModel>() {
            @Override
            public void onResponse(Call<SiteModel> call, Response<SiteModel> response) {
                if (response.code() == 201) {
                    progressBar.setVisibility(View.GONE);
                    String address_id = response.body().getId();
                    Toast.makeText(getApplicationContext(), "Address added", Toast.LENGTH_SHORT).show();
                    RequestBody address_id1 =
                            RequestBody.create(MediaType.parse("multipart/form-data"), address_id);
                    RequestBody name =
                            RequestBody.create(MediaType.parse("multipart/form-data"), name1);
                    RequestBody area1 =
                            RequestBody.create(MediaType.parse("multipart/form-data"), area);
                    RequestBody price1 =
                            RequestBody.create(MediaType.parse("multipart/form-data"), price);
                    RequestBody description1 =
                            RequestBody.create(MediaType.parse("multipart/form-data"), description);
                    RequestBody id =
                            RequestBody.create(MediaType.parse("multipart/form-data"),yourprefrence.getData(ConstantClass.ID));

                    site(name, address_id1,area1,price1,description1,id, body1, body2);
                    Log.e( "onResponse: ","id"+site_location );
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Faild to save Address", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SiteModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

            }
        });
    }
    private void site(RequestBody name, RequestBody address_id1,RequestBody area,RequestBody price,RequestBody description,RequestBody id, MultipartBody.Part body1, MultipartBody.Part body2) {
        Call<SiteModel> userResponse = ApiClient.getUserService()
                .addsite("Bearer " + yourprefrence.getData(ConstantClass.TOKEN)
                        , name, address_id1,area,price,description,id, body1, body2);
        userResponse.enqueue(new Callback<SiteModel>() {
            @Override
            public void onResponse(Call<SiteModel> call, Response<SiteModel> response) {
                Log.e("Tag", "response" + response.code());
                if (response.code() == 201) {
                    progressBar.setVisibility(View.GONE);
                        yourprefrence.saveData(SiteUtils.ID,response.body().getId());
                        startActivity(new Intent(AddSiteActivity.this,NumberOfPlotActivity.class));
                    Toast.makeText(getApplicationContext(), "Site Added Sucessfully"+response.body().getId(), Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Site Added Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SiteModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("Tag", "response" + t);
                Toast.makeText(getApplicationContext(), "Site Added Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}