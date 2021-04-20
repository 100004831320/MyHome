package com.example.administrator.smarthome.utils;

import android.app.ActivityManager;
import android.os.Handler;

/**
 * Created by Administrator on 2021/4/10 0010.
 */

public class ThreadUtils {
    /**子线程执行task*/
    public static void runInTread(Runnable task){
        new Thread(task).start();
    }
    /**主线程里面的Hander*/
    public static Handler mHandler = new Handler();
    /**UI线程执行task*/
    public static void runInUITread(Runnable task){
        mHandler.post(task);
    }
}
