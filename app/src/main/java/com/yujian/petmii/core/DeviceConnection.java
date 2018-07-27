package com.yujian.petmii.core;

import com.yujian.petmii.global.Constants;
import com.yujian.petmii.global.Session;
import com.yujian.petmii.utils.L;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lisic on 2018/7/21.
 */

public class DeviceConnection {

    private DeviceConnection(){}

    private static String sHostIp = "";
    private static String sHostMac = "";

    public static Observable<String> send(String data)
    {
        return Observable.fromCallable(() -> {
            DatagramSocket ds = null;
            String reply = "";
            try {
                ds = new DatagramSocket();
                ds.setSoTimeout(3000);
                byte[] bstr = data.getBytes();
                printData(bstr);
                DatagramPacket dp = new DatagramPacket(bstr, bstr.length,
                        InetAddress.getByName(sHostIp), 9200);
                ds.send(dp);
                byte[] buf = new byte[1024];
                DatagramPacket recvPackage = new DatagramPacket(buf,buf.length);
                ds.receive(recvPackage);

                reply = new String(recvPackage.getData(),
                        0,recvPackage.getLength());
            } finally {
                if(ds != null){
                    ds.close();
                }
            }
            return reply;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<byte[]> send(byte[] data)
    {
        return Observable.fromCallable(() -> {
            DatagramSocket ds = null;
            byte[] reply ;
            try {
                ds = new DatagramSocket();
                ds.setSoTimeout(5000);
                DatagramPacket dp = new DatagramPacket(data, data.length,
                        InetAddress.getByName(sHostIp), 9200);
                ds.send(dp);
                byte[] buf = new byte[1024];
                DatagramPacket recvPackage = new DatagramPacket(buf,buf.length);
                ds.receive(recvPackage);
                reply = recvPackage.getData();
            } finally {
                if(ds != null){
                    ds.close();
                }
            }
            return reply;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static void printData(byte[] data)
    {

        StringBuilder builder = new StringBuilder();
        for(int i=0;i<data.length;i++){
            builder.append(String.format("%x",data[i])).append("  ");
        }
        L.d(Constants.Tag,"data length=>"+data.length+",data=>"+builder.toString());
    }
    public static void initConnection(String mac,String ip){
        sHostIp = ip;
        sHostMac = mac;
        L.d(Constants.Tag,"hostMac==>"+mac+",hostIp==>"+ip);
        initDeviceInner();
    }

    public static void initDeviceInner()
    {
        byte[] userinfo = new byte[48];
        byte[] uheader = new byte[]{
                (byte)0xAA,0x5A,0x00,0x01,
                0x01,0x02,(byte)0xF0,0x01,0x00};//9

        System.arraycopy(uheader, 0, userinfo, 0, uheader.length);

        byte[] usernum = Session.inst().userNumber.getBytes();
        System.arraycopy(usernum, 0, userinfo, 9, usernum.length);

        byte[] token = Session.inst().token.substring(0,16).getBytes();
        System.arraycopy(token, 0, userinfo, 17, token.length);

        L.d(Constants.Tag,"==config user info==");
        printData(userinfo);
        send(userinfo).subscribe(reply->{
            L.d(Constants.Tag,"=config user reply=");
            printData(reply);
        },error->{
            L.d(Constants.Tag,"config user exception ==>"+error.getMessage());
        });//发送用户信息


        byte[] serverinfo = new byte[48];
        byte[] sheader = new byte[]{
                (byte)0xAA,0x5A,0x00,0x01,
                0x01,0x02,(byte)0xF0,0x02,0x00};
        System.arraycopy(sheader, 0, serverinfo, 0, sheader.length);

        byte[] port = new byte[2];
        port[0] = (byte)((Constants.Config.SEVER_PORT >> 8) & 0xFF);
        port[1] = (byte)(Constants.Config.SEVER_PORT & 0xFF);
        System.arraycopy(port, 0, serverinfo, 9, port.length);

        byte[] ips = Constants.Config.SERVER_IP.getBytes();
        System.arraycopy(ips, 0, serverinfo, 11, ips.length);

        printData(serverinfo);
        L.d(Constants.Tag,"==config server info==");
        send(serverinfo).subscribe(reply->{
            L.d(Constants.Tag,"=config server reply=");
            printData(reply);
        },error->{
            L.d(Constants.Tag,"config server exception ==>"+error.getMessage());
        });//发送服务器信息
    }

}
