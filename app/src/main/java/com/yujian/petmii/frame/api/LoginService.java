package com.yujian.petmii.frame.api;

import com.yujian.petmii.frame.entity.LoginResponseEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lisic on 2018/7/17.
 */

public interface LoginService {
    @FormUrlEncoded
    @POST("project/reg.php")
    Observable<LoginResponseEntity> login(@Field("username")String username,
                                          @Field("password")String password);
}
