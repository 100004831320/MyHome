package com.example.administrator.smarthome;

import android.content.Context;
import android.content.Intent;
import android.media.ThumbnailUtils;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.smarthome.activity.LoginActivity;
import com.example.administrator.smarthome.utils.ThreadUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    /*private String sql = "select * from lyx";
    DBManager dbManager = new DBManager();
    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    private List<Map<String,Object>> lists;
    private SimpleAdapter adapter;
    private ListView listView;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //停留3s进入进入主界面
        ThreadUtils.runInTread(new Runnable() {
            @Override
            public void run() {
                //停留3s
                SystemClock.sleep(3000);

                //进入进入主界面
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /*listView = (ListView) findViewById(R.id.EquipmentList);
        lists = new ArrayList<>();

        new Thread(){
            public void run(){
                try{
                    connection = dbManager.ConnectionMysql();
                    preparedStatement = connection.prepareStatement(sql);
                    if(preparedStatement == null ){

                        System.out.print("wwwwwww");
                    }else{
                        resultSet = preparedStatement.executeQuery();
                        while(resultSet.next()){
                            Map<String,Object> map =new HashMap<>();
                            int serialnumber = resultSet.getInt("serialnumber");
                            int temperature = resultSet.getInt("temperature");
                            int humidity = resultSet.getInt("humidity");
                            int SmokeConcentration = resultSet.getInt("SmokeConcentration");
                            map.put("serialnumber",serialnumber);
                            map.put("temperature",temperature);
                            map.put("humidity",humidity);
                            map.put("SmokeConcentration",SmokeConcentration);
                            lists.add(map);
                        }
                        adapter = new SimpleAdapter(MainActivity.this,lists,R.layout.list_equipment
                                ,new String[]{"serialnumber","temperature","humidity","SmokeConcentration"}
                                ,new int []{R.id.Name_list,R.id.Temperature,R.id.Humidity,R.id.Smoke_concentration});
                        listView.setAdapter(adapter);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                finally{
                    //7.关闭连接(顺序:后打开的先关闭)
                    if(statement != null)
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                    if(connection != null)
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }
                }
            }
        }.start();*/
    }

    /*private void checkLogin(User user) {
        DBThread dt = new DBThread();
        dt.setContext(this);
        Thread thread = new Thread(dt);
        thread.start();
    }*/
}

