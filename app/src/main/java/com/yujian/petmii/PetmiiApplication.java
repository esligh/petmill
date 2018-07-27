package com.yujian.petmii;

import android.app.Application;
import android.content.Context;

import com.yujian.petmii.global.Constants;


/**
 * Created by lisic on 2018/4/6.
 */

public class PetmiiApplication extends Application{

    private static PetmiiApplication sInstance ;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
    }

    public static Context getContext()
    {
        return sInstance;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
