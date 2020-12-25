package com.example.util;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

    // 登录请求
    public static void sendOkHttpRequestLogin(String address , Admin admin, okhttp3.Callback callback){

        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("a_username",admin.getA_username())
                .add("a_password",admin.getA_password())
                .build();

        Request request = new Request
                .Builder()
                .url(address)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    // 查询学生列表
    public static void sendOkHttpRequestStudent(String address, Student student,int pageIndex,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("s_name",student.getS_name())
                .add("s_studentid",String.valueOf(student.getS_studentid()))
                .add("s_age",String.valueOf(student.getS_age()))
                .add("pageIndex",String.valueOf(pageIndex))
                .build();
        Request request = new Request
                .Builder()
                .url(address)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    // 删除学生
    public static void sendOkHttpRequestDelStudent(String address, Student student,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("s_id",String.valueOf(student.getS_id()))
                .build();
        Request request = new Request
                .Builder()
                .url(address)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    // 查询学生
    public static void sendOkHttpRequestFindStudentByID(String address, Student student,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("s_id",String.valueOf(student.getS_id()))
                .build();
        Request request = new Request
                .Builder()
                .url(address)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    // 修改学生
    public static void sendOkHttpRequestUpdataStudent(String address, Student student,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("s_name",student.getS_name())
                .add("s_studentid",String.valueOf(student.getS_studentid()))
                .add("s_age",String.valueOf(student.getS_age()))
                .add("s_sex",String.valueOf(student.getS_sex()))
                .add("s_id",String.valueOf(student.getS_id()) )
                .build();
        Request request = new Request
                .Builder()
                .url(address)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }


}
