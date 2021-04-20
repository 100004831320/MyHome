package com.example.administrator.smarthome.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.administrator.smarthome.Datas.UserDevice;
import com.example.administrator.smarthome.Myadapter;
import com.example.administrator.smarthome.R;
import com.example.administrator.smarthome.utils.ThreadUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainInterface extends AppCompatActivity {


    private static String driver = "com.mysql.jdbc.Driver";//MySQL 驱动
    private static String url = "jdbc:mysql://47.105.70.99:3306/environmental?serverTimezone=UTC";//MYSQL数据库连接Url
    private static String user = "root";//用户名
    private static String password = "root";//密码
    String sql;
    ResultSet res = null;

    private Myadapter adapter;
    List<UserDevice> lists;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        listView = findViewById(R.id.EquipmentList);

        lists = new ArrayList<>();

        ThreadUtils.runInTread(new Runnable() {
            @Override
            public void run() {
                Connection conn = null;
                try {
                    Class.forName(driver); //
                    //conn = ConnectionManager.getInstance().getConnection();
                    conn = DriverManager.getConnection(url, user, password);
                    sql = "select * from lyx";
                    PreparedStatement st = conn.prepareStatement(sql);
                    res = st.executeQuery();
                    if (res == null) {
                        return;
                    }
                    int cnt = res.getMetaData().getColumnCount();
                    while (res.next()){
                        int serialnumber = res.getInt("serialnumber");
                        int temperature = res.getInt("temperature");
                        int humidity = res.getInt("humidity");
                        int SmokeConcentration = res.getInt("SmokeConcentration");
                        UserDevice data = new UserDevice(serialnumber,temperature,humidity,SmokeConcentration);
                        /*map.put("serialnumber", serialnumber);
                        map.put("temperature", temperature);
                        map.put("humidity", humidity);
                        map.put("SmokeConcentration", SmokeConcentration);*/
                        //str = "hhhh";
                        lists.add(data);
                    }
                    conn.close();
                    st.close();
                    res.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adapter = new Myadapter(MainInterface.this,R.layout.list_equipment,lists);
        listView.setAdapter(adapter);
    }
}
