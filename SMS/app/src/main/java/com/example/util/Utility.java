package com.example.util;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Utility {

    public static boolean handLeAdminResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                // JSONArray allProvinces = new JSONArray(response);

                JSONObject adminObject = new JSONObject(response);
                Admin admin = new Admin();
                admin.setA_id(adminObject.getInt("a_id"));
                admin.setA_username(adminObject.getString("a_username"));
                admin.setA_name(adminObject.getString("a_name"));
                admin.setA_phone(adminObject.getInt("a_phone"));
                admin.setA_describe(adminObject.getString("a_describe"));
                admin.setA_power(adminObject.getString("a_power"));
                admin.save();
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handLeStudentResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                Gson gson = new Gson();
                Map map = gson.fromJson(response, Map.class);
                response = map.get("list").toString();

                JSONArray allStudent = new JSONArray(response);
                for (int i = 0; i < allStudent.length(); i++) {
                    JSONObject studentObject = allStudent.getJSONObject(i);
                    Student student = new Student(studentObject.getInt("s_id"),
                            studentObject.getInt("s_studentid"),
                            studentObject.getString("s_name"),
                            studentObject.getString("s_sex"),
                            studentObject.getInt("s_age"),
                            studentObject.getInt("s_phone"),
                            studentObject.getInt("s_classid"),
                            studentObject.getString("s_classname"),
                            studentObject.getInt("s_dormitoryid")
                    );
                    student.save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }


}
