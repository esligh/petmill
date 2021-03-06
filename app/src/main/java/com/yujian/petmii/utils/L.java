<<<<<<< HEAD
package com.yujian.petmii.utils;

import android.util.Log;

import com.yujian.petmii.BuildConfig;
import com.yujian.petmii.global.Constants;


/**
 * 日志管理类
 * 
 * @author fr
 * 
 */
public class L  
{   
    private L()  
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");  
    }  
  
    private static boolean isDebug = BuildConfig.DEBUG;
    private static String TAG = Constants.Tag;
    
    public static void setTag(String tag,boolean debug)
    {
    	TAG = tag;
    	isDebug = debug;
    }
    
    // 下面四个是默认tag的函数  
    public static void i(String msg)  
    {  
        if (isDebug)  
            Log.i(TAG, msg);  
    }  
  
    public static void d(String msg)  
    {  
        if (isDebug)  
            Log.d(TAG, msg);  
    }  
  
    public static void e(String msg)  
    {  
        if (isDebug)  
            Log.e(TAG, msg);  
    }  
  
    public static void v(String msg)  
    {  
        if (isDebug)  
            Log.v(TAG, msg);  
    }  
  
    // 下面是传入自定义tag的函数  
    public static void i(String tag, String msg)  
    {  
        if (isDebug)  
            Log.i(tag, msg);  
    }  
  
    public static void d(String tag, String msg)  
    {  
        if (isDebug)  
            Log.i(tag, msg);  
    }  
  
    public static void e(String tag, String msg)  
    {  
        if (isDebug)  
            Log.i(tag, msg);  
    }  
  
    public static void v(String tag, String msg)  
    {  
        if (isDebug)  
            Log.i(tag, msg);  
    }  
=======
package com.yujian.petmii.utils;

import android.util.Log;

import com.yujian.petmii.BuildConfig;


/**
 * 日志管理类
 * 
 * @author fr
 * 
 */
public class L  
{   
    private L()  
    {  
        /* cannot be instantiated */  
        throw new UnsupportedOperationException("cannot be instantiated");  
    }  
  
    private static boolean isDebug = BuildConfig.DEBUG;
    private static String TAG = "L";
    
    public static void setTag(String tag,boolean debug)
    {
    	TAG = tag;
    	isDebug = debug;
    }
    
    // 下面四个是默认tag的函数  
    public static void i(String msg)  
    {  
        if (isDebug)  
            Log.i(TAG, msg);  
    }  
  
    public static void d(String msg)  
    {  
        if (isDebug)  
            Log.d(TAG, msg);  
    }  
  
    public static void e(String msg)  
    {  
        if (isDebug)  
            Log.e(TAG, msg);  
    }  
  
    public static void v(String msg)  
    {  
        if (isDebug)  
            Log.v(TAG, msg);  
    }  
  
    // 下面是传入自定义tag的函数  
    public static void i(String tag, String msg)  
    {  
        if (isDebug)  
            Log.i(tag, msg);  
    }  
  
    public static void d(String tag, String msg)  
    {  
        if (isDebug)  
            Log.i(tag, msg);  
    }  
  
    public static void e(String tag, String msg)  
    {  
        if (isDebug)  
            Log.i(tag, msg);  
    }  
  
    public static void v(String tag, String msg)  
    {  
        if (isDebug)  
            Log.i(tag, msg);  
    }  
>>>>>>> 235af1f2e7dcd142969589321679241a085334a2
}  