package com.yujian.petmii.frame.api;

import com.yujian.petmii.frame.entity.LoginResponseEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lisic on 2018/7/17.
 */

public interface RegisterService {
//    @FormUrlEncoded
//    @POST("project/reg.php")
//    Observable<LoginResponseEntity> register(@Field("username")String username,
//                                          @Field("password")String password,
//                                          @Field("verf_code")String vcode);
@Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("project/reg.php")
    Observable<LoginResponseEntity> register(@Body RequestBody body);
}
