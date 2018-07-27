package com.yujian.petmii.frame.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by fr on 2018/7/20.
 */

public interface SessionService {

//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @POST("project/reg.php")
//    Observable<LoginResponseEntity> register(@Body RequestBody body);

    @FormUrlEncoded
    @POST("project/reg.php")
    Observable<ResponseBody> register(@Field("username")String username,
                                      @Field("password")String password,
                                      @Field("verf_code")String vcode);

//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @POST("project/login.php")
//    Observable<ResponseBody> login(@Body RequestBody route);

    @FormUrlEncoded
    @POST("project/login.php")
    Observable<ResponseBody> login(@Field("username")String username,
                                   @Field("password")String password);
}
