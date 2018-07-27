package com.yujian.petmii.frame.entity;

import java.io.Serializable;

/**
 * Created by lisic on 2018/4/6.
 */

public class ProductInfo implements Serializable{

    public int    logo;
    public String name;
    public String extra;

    public ProductInfo(int logo,String name,String extra)
    {
        this.name = name;
        this.logo = logo;
        this.extra = extra;
    }
}
