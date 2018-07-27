package com.yujian.petmii.frame.api;

import io.reactivex.Observable;
import okhttp3.RequestBody;
<<<<<<< HEAD
import okhttp3.ResponseBody;
=======
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lisic on 2018/7/17.
 */

public interface LoginService {

    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("project/login.php")
<<<<<<< HEAD
    Observable<ResponseBody> login(@Body RequestBody route);


=======
    Observable<LoginResponseEntity> login(@Body RequestBody route);
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2
}
