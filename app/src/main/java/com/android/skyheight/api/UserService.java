package com.android.skyheight.api;



import com.android.skyheight.model.LoginModel;
import com.android.skyheight.model.UserDetail;
import com.android.skyheight.model.UserList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {
    String BASE_URL = "https://testappecom.herokuapp.com";
    @POST("user/register/")
    Call<UserDetail> createUser(@Body UserDetail userDetail);
    @FormUrlEncoded
    @POST("user/login/")
    Call<LoginModel> user_login(@Field("mobile_number") String mobile_number,
                                @Field("password") String password);
    @GET("user")
    Call<ArrayList<UserList>> allusers();
}
