package com.yujian.petmii.frame.entity;

/**
 * Created by fr on 2018/7/19.
 */

public class WifiInfo {

    public String ssid;
    public String bssid;
    public int frequency;

    public WifiInfo(String ssid, String bssid, int freq){
        this.ssid = ssid;
        this.bssid = bssid;
        this.frequency = freq;
    }

    @Override
    public String toString() {
        return ssid;
    }
}
