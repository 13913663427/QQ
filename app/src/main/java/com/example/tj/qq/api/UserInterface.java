package com.example.tj.qq.api;

import com.example.tj.qq.item.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserInterface {
//    @GET("login/cellphone")
//    Call<UserItem> getUsers(@Query("phone") String phone, @Query("password") String password);

    @POST("user/login")
    @FormUrlEncoded
    Call<User> login(@Field("userName") String userName, @Field("password") String password);
}
