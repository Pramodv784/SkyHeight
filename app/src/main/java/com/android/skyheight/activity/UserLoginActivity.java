package com.android.skyheight.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.skyheight.R;
import com.android.skyheight.api.ApiClient;
import com.android.skyheight.model.LoginModel;
import com.android.skyheight.utils.ConstantClass;
import com.android.skyheight.utils.Prefrence;
import com.android.skyheight.utils.ProgressButton;
import com.google.android.material.snackbar.Snackbar;

public class UserLoginActivity extends AppCompatActivity {
EditText mobile,password;
Prefrence yourprefrence;
RelativeLayout relativeLayout;
ProgressBar progressBar;
TextView login,skip;
ConstraintLayout constraintLayout;

    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
    mobile = findViewById(R.id.mobile);
    password = findViewById(R.id.password);
    relativeLayout=findViewById(R.id.relative);
    progressBar = findViewById(R.id.progressbar);
    login = findViewById(R.id.login);
    constraintLayout=findViewById(R.id.constraint);
        view =findViewById(R.id.myprogressbutton);
    yourprefrence = Prefrence.getInstance(UserLoginActivity.this);
       // password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        //password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    skip=findViewById(R.id.skip);
        SpannableString content = new SpannableString("skip");
        content.setSpan(new UnderlineSpan(),0,content.length(),0);
        skip.setText(content);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLoginActivity.this,HomeActivity.class));
                
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (s.toString().length()==0){
                    constraintLayout.setBackgroundResource(R.drawable.layout);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()>0){
                    constraintLayout.setBackgroundResource(R.drawable.btn);
                    login.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }
        });

    }
    public void ulogin(View view) {
        String m =mobile.getText().toString();
        String p =password.getText().toString();



        if (m.isEmpty() && p.isEmpty()){
            mobile.setError("Enter mobile");
            password.setError("Enter password");
        }
        else if (m.isEmpty()){
            mobile.setError("Enter Mobile Number");
            mobile.requestFocus();
        }
        else if (p.isEmpty()){
            password.setError("Enter Mobile Number");
            password.requestFocus();
        }

       else if (!(m.length() >9)){
          mobile.setError("Enter 10 digit mobile number");
          mobile.requestFocus();
        }


        else {
            ButtonActivated();
            loginuser(m,p);

        }
    }

    private void loginuser(String m, String p) {
        Call<LoginModel> userResponseCall = ApiClient.getUserService().user_login(m,p);
        userResponseCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.code()==202) {
                    ButtonFinished();
               yourprefrence.saveData(ConstantClass.TOKEN,response.body().getToken());
               yourprefrence.saveData(ConstantClass.MOBILE_NUMBER,response.body().getMobile_number());
               yourprefrence.saveData(ConstantClass.PASSWORD,response.body().getPassword());
               yourprefrence.saveData(ConstantClass.TYPE,response.body().getType());
               yourprefrence.saveData(ConstantClass.USERNAME,response.body().getUsername());
               yourprefrence.saveData(ConstantClass.ADDRESS,response.body().getAddress());
                    ButtonFinished();
                startActivity(new Intent(UserLoginActivity.this,HomeActivity.class));
                }
                else {
                    ButtonFinished();
                    Snackbar.make(relativeLayout, R.string.exit, Snackbar.LENGTH_LONG);
                    Toast.makeText(getApplicationContext(),"Mobile Number or Password Wrong ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                ButtonFinished();
                Snackbar.make(relativeLayout, R.string.exit, Snackbar.LENGTH_LONG);
                Toast.makeText(getApplicationContext()," Check Internet Connection Some thing went wrong ", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ButtonActivated() {
        progressBar.setVisibility(View.VISIBLE);
        login.setText("Please Wait.....");
    }
    public void ButtonFinished(){
        progressBar.setVisibility(View.GONE);
        login.setText("Login");
    }
    public void register(View view) {
        startActivity(new Intent(UserLoginActivity.this,SignupActivity.class));
    }

    @Override
    public void onBackPressed() {
        Snackbar.make(relativeLayout, "Are you Sure want to exit", Snackbar.LENGTH_LONG)
                .setAction(R.string.close, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finishAffinity();
                    }
                }).setActionTextColor(getResources().getColor(R.color.colorPrimary)).show();
    }
}