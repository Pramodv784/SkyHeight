package com.android.skyheight.api;



import com.android.skyheight.model.AddressModel;
import com.android.skyheight.model.DeleteModel;
import com.android.skyheight.model.LoginModel;
import com.android.skyheight.model.PlotListModel;
import com.android.skyheight.model.PlotsModel;
import com.android.skyheight.model.SiteListModel;
import com.android.skyheight.model.SiteModel;
import com.android.skyheight.model.UserDetail;
import com.android.skyheight.model.UserList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    String BASE_URL = "https://testappecom.herokuapp.com";
    @POST("user/register/")
    Call<UserDetail> createUser(@Body UserDetail userDetail);
    @FormUrlEncoded
    @POST("user/login/")
    Call<LoginModel> user_login(@Field("mobile_number") String mobile_number,
                                @Field("password") String password);
    @GET("user")
    Call<ArrayList<UserList>> allusers(@Header("Authorization") String token);
    @DELETE("user/{id}")
    Call<DeleteModel> delete(@Header("Authorization") String token,@Path("id") String id);


    @POST("site/address")
    Call<SiteModel> addaddress(@Header("Authorization") String token, @Body AddressModel addressModel);
    @Multipart
    @POST("site/")
    Call<SiteModel> addsite(@Header("Authorization") String token,
                               @Part("name") RequestBody name,
                               @Part("site_location") RequestBody site_location,
                               @Part("area") RequestBody site_area,
                               @Part("price") RequestBody site_price,
                               @Part("description") RequestBody site_description,
                               @Part("owner") RequestBody id,
                               @Part MultipartBody.Part image,
                               @Part MultipartBody.Part file);
    @GET("site")
    Call<ArrayList<SiteListModel>> sitelist(@Header("Authorization") String token);
    @GET("user")
    Call<ArrayList<UserList>> allbroker(@Header("Authorization") String token,
                                        @Query("type") String type);
    @GET("site/plot")
    Call<ArrayList<PlotListModel>> allplot(@Header("Authorization") String token);
    @POST("site/{id}/plots/{plots_count}")
    Call<PlotsModel> plots(@Header("Authorization") String token,
                           @Path("id") String id,@Path("plots_count") String plots_count);
    @DELETE("site/{id}")
    Call<DeleteModel> deletesite(@Header("Authorization") String token,@Path("id") String delete);
    @GET("site/plot")
    Call<ArrayList<PlotListModel>> siteplot(@Header("Authorization") String token,@Query("site") String site);

}
