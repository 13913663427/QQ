package com.example.tj.qq.api;

import com.example.tj.qq.item.UserItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecycleNetInterface {

    @GET("2452/getUser")
    Call<List<UserItem>> getUsers();
}
