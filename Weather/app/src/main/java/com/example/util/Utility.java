package com.example.util;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.example.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    public static boolean handLeProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allProvinces = new JSONArray(response);
                for(int i=0; i<allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProviceName(provinceObject.getString("name"));
                    province.setProviceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handLeCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCity = new JSONArray(response);
                for(int i=0; i<allCity.length();i++){
                    JSONObject cityObject = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("cityname"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handLeCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounty = new JSONArray(response);
                for(int i=0; i<allCounty.length();i++){
                    JSONObject countyObject = allCounty.getJSONObject(i);
                    // Log.d("TAG", "handLeCountyResponse: "+countyObject);
                    County county = new County();
                    county.setId(countyObject.getInt("countyid"));
                    county.setCountyName(countyObject.getString("countyname"));
                    county.setWeatherId(countyObject.getString("weatherid"));
                    county.setCityId(cityId);
                    // Log.d("TAG", "handLeCountyResponse: "+county.toString());
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("TAG", "handLeCountyResponse: erroy");

            }
        }
        return false;
    }

    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
