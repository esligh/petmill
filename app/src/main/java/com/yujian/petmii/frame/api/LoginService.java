package com.yujian.petmii.frame.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lisic on 2018/7/17.
 */

public interface LoginService {

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("project/login.php")
    Observable<ResponseBody> login(@Body RequestBody route);


}
