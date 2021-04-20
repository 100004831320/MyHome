package com.example.administrator.smarthome.Datas;

/**
 * Created by Administrator on 2021/4/19 0019.
 */

public class UserDevice {
    public int serialnumber;
    public int temperature;
    public int humidity;
    public int SmokeConcentration;
    public UserDevice(int se, int te, int hu, int sm){
        this.serialnumber = se;
        this.temperature = te;
        this.humidity = hu;
        this.SmokeConcentration = sm;
    }
    public int getserialnumber(){
        return serialnumber;
    }

    public int gettemperature(){
        return  temperature;
    }
    public int gethumidity(){
        return  humidity;
    }
    public int getSmokeConcentration(){
        return  SmokeConcentration;
    }
}
