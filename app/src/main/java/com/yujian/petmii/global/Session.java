package com.yujian.petmii.global;

import android.text.TextUtils;

import com.yujian.petmii.PetmiiApplication;
import com.yujian.petmii.utils.DES3;
import com.yujian.petmii.utils.L;
import com.yujian.petmii.utils.PreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lisic on 2018/7/17.
 */

public class Session {

    public String token;
    public String userName;
    public String nickName;
    public String userSign;
    public String userInfo; //3DES加密的用户信息 包含了用户编号和随机码 使用;分隔
    public String userIcon;
    public String cdn;

    public String userNumber; //用户编号
    public String randCode; //随机码

    public boolean bValid;

    public static Session sInstance = new Session();

    public void saveSession(String sessionStr)
    {
        readSession(sessionStr);
        PreferencesUtils.putString(PetmiiApplication.getContext(),Constants.PrefKey.SESSION,
                sessionStr);

    }

    private void readSession(String sessionStr) {
        try {
            JSONObject obj = new JSONObject(sessionStr);
            token = obj.optString("token");
            userName = obj.optString("user_name");
            nickName = obj.optString("nick_name");
            userSign = obj.optString("user_sign");
            userInfo = obj.optString("user_info");
            userIcon = obj.optString("user_icon");
            cdn = obj.optString("CDN");
            String str = DES3.decode(userInfo);
            L.d("userinfo==>"+str);
            if(!TextUtils.isEmpty(str)){
                String[] result = str.split(";");
                if(result.length == 2){
                    userNumber = result[0];
                    randCode = result[1];
                    L.d("userNumber==>"+userNumber+",randCode===>"+randCode);
                }
            }
            bValid = true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean withSession()
    {
        if(!bValid){
            String sessionStr = PreferencesUtils.getString(PetmiiApplication.getContext(),Constants.PrefKey.SESSION,"");
            if(TextUtils.isEmpty(sessionStr)){
                return false;
            }else{
                readSession(sessionStr);
                return true;
            }
        }
        return true;
    }


    public static Session inst(){
        return sInstance;
    }

}
