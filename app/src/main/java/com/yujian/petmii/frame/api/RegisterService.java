package com.yujian.petmii.frame.api;

import io.reactivex.Observable;
<<<<<<< HEAD
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
=======
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2
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
<<<<<<< HEAD
    Observable<ResponseBody> register(@Field("username")String username,
                                      @Field("password")String password,
                                      @Field("verf_code")String vcode);
//    @Headers({"Content-Type: application/json","Accept: application/json"})
//    @POST("project/reg.php")
//    Observable<LoginResponseEntity> register(@Body RequestBody body);
=======
    Observable<LoginResponseEntity> register(@Body RequestBody body);
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2
}
