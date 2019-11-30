package com.channnxy.school_bus;

import android.app.Application;

public class GlobalData extends Application {
//    登录态 -1未登录 0老师 1学生 2司机
    public static int loginType;
    public static int loginMode;
    public static String username;
    public static String url = "http://117.50.22.42:3000/";
}
