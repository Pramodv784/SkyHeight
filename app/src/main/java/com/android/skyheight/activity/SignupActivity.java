package com.android.skyheight.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.UserDetail;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;
import com.android.skyheight.utils.ProgressButton;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener{
    TextInputLayout username,password,cpassword,mobile,address;
    Spinner type;
    Prefrence yourprefrence;
    String usertypeselected;
   UserDetail userDetail;
   TextView register;
   ProgressBar progressBar;
    String[] usertype = {"Select User","Customer","Broker","Owner","Co-Owner(Partner)","Super_Admin"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mobile = findViewById(R.id.mobile_number);
        address = findViewById(R.id.address);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        type = findViewById(R.id.type);
        register = findViewById(R.id.register);

        progressBar = findViewById(R.id.progressbar);
        yourprefrence= Prefrence.getInstance(SignupActivity.this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        type.setOnItemSelectedListener(this);


        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,R.layout.spinner_dropdown_layout,usertype);
        aa.setDropDownViewResource(R.layout.spinner_layout);
        //Setting the ArrayAdapter data on the Spinner
        type.setAdapter(aa);
    }
    public void userReg(View view) {
        String un = username.getEditText().getText().toString();
        String p = password.getEditText().getText().toString();
        String cp = cpassword.getEditText().getText().toString();
        String m = mobile.getEditText().getText().toString();
        String ad = address.getEditText().getText().toString();
        String t = type.getSelectedItem().toString().trim();

        if (un.isEmpty() && p.isEmpty() && cp.isEmpty() && m.isEmpty() && ad.isEmpty()) {
            username.setError("enter your name");
            password.setError("enter password");
            cpassword.setError("re enter your password");
            mobile.setError("Enter mobile");
            address.setError("Enter Address");
        } else if (p.isEmpty()) {
            password.setError("Enter password");
            password.requestFocus();
        } else if (cp.isEmpty()) {
            cpassword.setError("Re Enter password");
            cpassword.requestFocus();
        }
        else if (m.isEmpty()) {
            mobile.setError("enter mobile number");
            mobile.requestFocus();
        }
        else if (ad.isEmpty()) {
            mobile.setError("enter your address");
            mobile.requestFocus();
        }
        else if (t.equals("Select User")) {
           Toast.makeText(getApplicationContext(),"Please select Type User",Toast.LENGTH_SHORT).show();
        }
        else if (!(p.length()>8)) {
            password.setError("Password contains 8 characters");
            password.requestFocus();
        }else if (!(cp.equals(p))) {
            password.setError("Password not match ");
            password.requestFocus();
        }
        else if (!(m.length() > 9)) {
            mobile.setError("Enter 10 digit mobile");
            mobile.requestFocus();
        }

        else {
            ButtonActivated();
            UserDetail userDetail = new UserDetail(un,p,cp,m,usertypeselected,ad);
            saveuser(userDetail);
            //startActivity(new Intent(SignupActivity.this,UserLoginActivity.class));
        }
    }

    private void saveuser(UserDetail userDetail) {
        Call<UserDetail> userResponseCall = ApiClient.getUserService().createUser(userDetail);
        userResponseCall.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                if (response.code() == 201) {


                    yourprefrence.saveData(ConstantClass.USERNAME,response.body().getUsername());
                    yourprefrence.saveData(ConstantClass.PASSWORD,response.body().getPassword());
                    yourprefrence.saveData(ConstantClass.CPASSWORD,response.body().getConfirm_password());
                    yourprefrence.saveData(ConstantClass.MOBILE_NUMBER,response.body().getMobile_number());
                    yourprefrence.saveData(ConstantClass.ADDRESS,response.body().getPassword());
                    yourprefrence.saveData(ConstantClass.TYPE,response.body().getType());
                    ButtonFinished();
                    showCustomDialog();

                }
                else{
                    ButtonFinished();
                    Toast.makeText(SignupActivity.this, "Enter Unique Mobile Or Username " , Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                ButtonFinished();
                Toast.makeText(SignupActivity.this, "Request failed " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.my_dialog);
        alertDialog.show();

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            usertypeselected = (String) parent.getItemAtPosition(position);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"Please select user type",Toast.LENGTH_SHORT).show();

    }
    public void ButtonActivated(){
        progressBar.setVisibility(View.VISIBLE);
        register.setText("Please Wait.....");
    }
        public void ButtonFinished(){
            progressBar.setVisibility(View.GONE);
            register.setText("Registration");
        }


    public void ok(View view) {
        startActivity(new Intent(SignupActivity.this,AdminViewActivity.class));
    }
}
