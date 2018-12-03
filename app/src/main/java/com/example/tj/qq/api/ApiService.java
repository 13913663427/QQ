package com.example.tj.qq.api;

import com.example.tj.qq.item.Message;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by QinZB on 2017/9/3.
 */
public interface ApiService {
    @POST("/user/login")
    @FormUrlEncoded
    Observable<Message> login(@Field("userName") String userName, @Field("password") String password);

}
