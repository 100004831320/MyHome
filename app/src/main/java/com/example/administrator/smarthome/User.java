package com.example.administrator.smarthome;

import java.io.Serializable;

/**
 * Created by Administrator on 2021/4/8 0008.
 */

public class User implements Serializable {
    private String name;
    private String pwd;
    private String phone;
    private String email;

    public User(){
    }
    public User(String name,String pwd,String phone,String email){
        this.phone = phone;
        this.phone = phone;
        this.phone = phone;
        this.email  = email;
    }

    public String getname() {
        return name;
    }
    public void setUname(String name){
        this.name = name;
    }
    public void setUpwd(String pwd){
        this.pwd = pwd;
    }
    public void setUphone(String phone){
        this.phone = phone;
    }
    public void setUemail(String email){
        this.email = email;
    }
}
