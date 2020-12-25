package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.util.ArrayUtil;
import com.example.util.City;
import com.example.util.County;
import com.example.util.HttpUtil;
import com.example.util.Utility;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Spinner province;
    private Spinner city;
    private Spinner county;
    private String[] provinceData;
    private String[] cityData;
    private List<String> cityListData;
    private String[] countyData;
    private List countyListData;
    private List<City> cityList;
    private List<County> countyList;
    private int provinceId = 0;
    private int cityId = 0;
    private String[] cityCodes = new String[]{"0"};



    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
           if(msg.what == 1 || msg.what == 2){
               ChangeSpinner(city,cityData);
           }else if(msg.what == 3 || msg.what == 4){
               ChangeSpinner(county,countyData);
           }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        province = findViewById(R.id.province);
        city = findViewById(R.id.city);
        county = findViewById(R.id.county);

        provinceData = new String[]{"请选择","北京","上海","天津","重庆","香港","澳门","台湾","黑龙江",
                "吉林","辽宁","内蒙古","河北","河南","山西","山东","江苏","浙江","福建","江西","安徽","湖北","湖南","广东",
                "广西","海南","贵州","云南","四川","西藏","陕西","宁夏","甘肃","青海","新疆"};
        ChangeSpinner(province,provinceData);


        // 选择省份 监听选择事件
        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //parent就是父控件spinner
            //view就是spinner内填充的textview,id=@android:id/text1
            //position是值所在数组的位置
            //id是值所在行的位置，一般来说与positin一致

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ArrayUtil arrayUtil = new ArrayUtil();
                provinceId = (int) id;
                // 查询数据库
                cityList = DataSupport.where("provinceId=?",""+provinceId).find(City.class);
                // 数据库有数据
                if(cityList.size()>0){
                    // Log.d("TAG", "数据库有数据");
                    cityData = new String[]{""};
                    cityCodes = new String[]{""};
                    for(City city : cityList){
                        // ArrayUtil arrayUtil = new ArrayUtil();
                        cityData = arrayUtil.push(cityData,city.getCityName());
                        cityCodes = arrayUtil.push(cityCodes,String.valueOf(city.getCityCode()));
                    }
                    arrayUtil.arrayPlay(cityCodes);

                    // arrayUtil.arrayPlay(cityData);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
                // 数据库无数据 请求数据
                else {
                    int provinceCode = provinceId;
                    String address = "http://10.0.2.2:8080/weatherCool/China/" + provinceCode;
                    Log.d("TAG", "数据库无数据"+address);
                    HttpUtil.sendOkHttpRequest(address, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("TAG", "请求失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            // Log.d("TAG", "数据库无数据请求数据");
                            String responText = response.body().string();
                            boolean result = false;
                            result = Utility.handLeCityResponse(responText, provinceCode);
                            if(result){
                               // Log.d("TAG", "请求后，继续查询数据库");
                                cityList = DataSupport.where("provinceId=?",""+provinceId).find(City.class);
                                cityData = new String[]{""};;
                                cityCodes = new String[]{""};
                                for(City city : cityList){
                                    cityData = arrayUtil.push(cityData,city.getCityName());
                                    cityCodes = arrayUtil.push(cityCodes,String.valueOf(city.getId()));
                                }
                                arrayUtil.arrayPlay(cityCodes);
                                Message message = new Message();
                                message.what = 2;
                                handler.sendMessage(message);
                            }else {
                                Toast.makeText(MainActivity.this,"请求失败！",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                //设置spinner内的填充文字居中
                //((TextView)view).setGravity(Gravity.CENTER);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        /**************************************************** 监听市的点击事件* **********************************************************/
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //parent就是父控件spinner
            //view就是spinner内填充的textview,id=@android:id/text1
            //position是值所在数组的位置
            //id是值所在行的位置，一般来说与positin一致
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                ArrayUtil arrayUtil = new ArrayUtil();

                int idCode = (int) id;

                if(cityList.size() != 0){
                    cityId = cityList.get(idCode).getCityCode();
                    Log.d("TAG", "onItemSelected: "+cityId);
                }


                // 查询数据库
                countyList = DataSupport.where("cityId=?",""+cityId).find(County.class);
                Log.d("TAG", "cityId=?"+cityId);
                // 数据库有数据
                if(countyList.size()>0){
                    Log.d("TAG", "数据库有数据");
                    countyData = new String[]{""};
                    for(County county : countyList){
                        countyData = arrayUtil.push(countyData,county.getCountyName());

                    }
                    // arrayUtil.arrayPlay(cityData);
                    Message message = new Message();
                    message.what = 3;
                    handler.sendMessage(message);
                }
                // 数据库无数据 请求数据
                else {
                    // countyData = new String[]{""};
                    int cityCode = cityId;
                    Log.d("TAG", "onItemSelected: "+cityCode);
                    String address = "http://10.0.2.2:8080/weatherCool/China/" + provinceId+"/"+cityCode;
                    Log.d("TAG", "数据库无数据"+address);
                    HttpUtil.sendOkHttpRequest(address, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("TAG", "请求失败");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            // Log.d("TAG", "数据库无数据请求数据");
                            String responText = response.body().string();
                            boolean result = false;
                            result = Utility.handLeCountyResponse(responText, cityCode);
                            if(result){
                                // Log.d("TAG", "数据库有数据");
                                countyData = new String[]{""};
                                countyList = DataSupport.where("cityId=?",""+cityCode).find(County.class);
                                Log.d("TAG", "请求后cityId=?"+cityId);
                                for(County county : countyList){
                                    // ArrayUtil arrayUtil = new ArrayUtil();
                                    countyData = arrayUtil.push(countyData,county.getCountyName());
                                }

                                // arrayUtil.arrayPlay(cityData);
                                Message message = new Message();
                                message.what = 4;
                                handler.sendMessage(message);
                            }else {
                                //Toast.makeText(MainActivity.this,"请求失败！",Toast.LENGTH_LONG).show();
                                Log.d("TAG", "onResponse: ");
                            }
                        }
                    });
                }
                //设置spinner内的填充文字居中
                //((TextView)view).setGravity(Gravity.CENTER);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });




    }


    /**
     * Spinner自定义样式
     * 1、Spinner内的TextView样式：item_select
     * 2、Spinner下拉中每个item的TextView样式：item_drop
     * 3、Spinner下拉框样式，属性设置
     * */
    public void ChangeSpinner(Spinner spinner, String[] data){
        spinner.setDropDownWidth(400); //下拉宽度
        spinner.setDropDownHorizontalOffset(100); //下拉的横向偏移
        spinner.setDropDownVerticalOffset(100); //下拉的纵向偏移
        //mSpinnerSimple.setBackgroundColor(AppUtil.getColor(instance,R.color.wx_bg_gray)); //下拉的背景色
        //spinner mode ： dropdown or dialog , just edit in layout xml
        //mSpinnerSimple.setPrompt("Spinner Title"); //弹出框标题，在dialog下有效

        //自定义选择填充后的字体样式
        //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.item_select, data);
        //自定义下拉的字体样式
        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        //这个在不同的Theme下，显示的效果是不同的
        //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
        spinner.setAdapter(spinnerAdapter);
    }





}