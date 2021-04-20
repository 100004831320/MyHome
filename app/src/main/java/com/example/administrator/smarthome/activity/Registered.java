package com.example.administrator.smarthome.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.smarthome.R;
import com.example.administrator.smarthome.server.SendEmailUtil;
import com.example.administrator.smarthome.utils.ThreadUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;

public class Registered extends AppCompatActivity {


    private static String driver = "com.mysql.jdbc.Driver";//MySQL 驱动
    private static String url = "jdbc:mysql://47.105.70.99:3306/environmental?useUnicode=true&characterEncoding=utf8";//MYSQL数据库连接Url
    private static String user = "root";//用户名
    private static String password = "root";//密码

    ResultSet res = null;

    String sql;

    private String reemail;
    private String repassword;
    private String againPassward;
    private String reverification;
    private String name = "ste";

    int code;

    TextView etEmail;
    TextView etPassWord;
    TextView againPassWord;
    TextView verification;
    Button determineBtnLogin;
    Button obtainBtnVerification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        initView();
        initListener();
    }

    private void initListener() {
        obtainBtnVerification.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                reemail = etEmail.getText().toString();
                repassword = etPassWord.getText().toString();
                againPassward = againPassWord.getText().toString();

                //判断用户名和密码是否为空
                if (TextUtils.isEmpty(reemail)) {
                    etEmail.setError("Email不能为空");
                    return;
                }
                if (TextUtils.isEmpty(repassword)) {
                    etPassWord.setError("密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(againPassward)) {
                    againPassWord.setError("密码不能为空");
                    return;
                }

                if (repassword.equals(againPassward)) {
                    SendEmailUtil sendEmailUtil = new SendEmailUtil();
                    try {
                        sendEmailUtil.sendEmail(reemail);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    againPassWord.setError("密码错误");
                }
            }
        });
        determineBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reverification = verification.getText().toString();
                if(reverification.equals(String.valueOf(code))){
                    ThreadUtils.runInTread(new Runnable() {
                        @Override
                        public void run() {
                            Connection conn = null;
                            try {

                                Class.forName(driver); //

                                //conn = ConnectionManager.getInstance().getConnection();
                                conn = DriverManager.getConnection(url,user,password);
                                sql = "insert into user(name,password,email,phone) values ("+ name+","+repassword+","+reemail+")";
                                PreparedStatement st = conn.prepareStatement(sql);
                                res = st.executeQuery();
                                Intent intent = new Intent(Registered.this, LoginActivity.class);
                                startActivity(intent);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }finally {
                                try {
                                    conn.close();
                                    res.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }else{
                    verification.setError("验证码错误");
                }
            }
        });
    }

    private void initView() {
        etEmail = findViewById(R.id.re_Email);
        etPassWord = findViewById(R.id.re_Password);
        againPassWord = findViewById(R.id.re_again_Password);
        verification = findViewById(R.id.re_verification);
        determineBtnLogin = findViewById(R.id.email_registered_button);
        obtainBtnVerification = findViewById(R.id.obtain_verification_button);
    }
}
