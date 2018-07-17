package com.yujian.petmii.utils;

import android.widget.Toast;

import com.yujian.petmii.PetmiiApplication;

/**
 * Created by lisic on 2018/7/17.
 */

public class ToastUtils {
    private ToastUtils(){}

    public static void shortShow(String msg){
        Toast.makeText(PetmiiApplication.getContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public static void shortShow(int resId){
        Toast.makeText(PetmiiApplication.getContext(),
                PetmiiApplication.getContext().getResources().getString(resId)
                ,Toast.LENGTH_SHORT).show();
    }

    public static void longShow(String msg){
        Toast.makeText(PetmiiApplication.getContext(),msg,Toast.LENGTH_LONG).show();
    }

    public static void longShow(int resId){
        Toast.makeText(PetmiiApplication.getContext(),
                PetmiiApplication.getContext().getResources().getString(resId)
                ,Toast.LENGTH_LONG).show();
    }
}
