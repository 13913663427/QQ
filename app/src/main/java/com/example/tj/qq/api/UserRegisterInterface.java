package com.example.tj.qq.api;

import com.example.tj.qq.item.User;
import com.example.tj.qq.item.User1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRegisterInterface {
    @POST("user/register")
    Call<User> register(@Body User1 user1);
}
