package com.yujian.petmii.global;

import com.yujian.petmii.frame.entity.SessionEntity;

/**
 * Created by lisic on 2018/7/17.
 */

public class Session {
    private static Session sInstance;

    private Session(){

    }

    public static void saveSession(SessionEntity entity)
    {

    }

    private Session getSession(){
        return sInstance;
    }

}
