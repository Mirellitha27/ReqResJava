package com.iwebsapp.reqresjava.api;

import com.iwebsapp.reqresjava.model.account.ResponseRegister;
import com.iwebsapp.reqresjava.model.detail_home.DetailHomeModel;
import com.iwebsapp.reqresjava.model.home.HomeModel;
import com.iwebsapp.reqresjava.model.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseLogin> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/register")
    Call<ResponseRegister> register(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/users")
    Call<HomeModel> getUser();

    @GET("api/users/{idUser}")
    Call<DetailHomeModel> getCurrentUser(
            @Path("idUser") String idUser
    );

}
