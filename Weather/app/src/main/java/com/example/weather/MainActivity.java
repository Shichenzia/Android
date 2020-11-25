package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner province;
    private Spinner city;
    private Spinner area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        province = findViewById(R.id.province);

        String [] provinceData = new String[]{"北京","上海","天津","重庆","香港","澳门","台湾","黑龙江","吉林","辽宁","内蒙古","河北","河南","山西","山东","江苏","浙江","福建","江西","安徽","湖北","湖南","广东","广西","海南","贵州","云南","四川","西藏","陕西","宁夏","甘肃","青海","新疆"};

        ChangeSpinner(province,provinceData);

        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //parent就是父控件spinner
            //view就是spinner内填充的textview,id=@android:id/text1
            //position是值所在数组的位置
            //id是值所在行的位置，一般来说与positin一致

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Log.d("TAG","onItemSelected : parent.id="+parent.getId()+
                        ",isSpinnerId="+(parent.getId() == R.id.province)+
                        ",viewid="+view.getId()+ ",pos="+pos+",id="+id);
//                LogUtil.i("onItemSelected : parent.id="+parent.getId()+
//                        ",isSpinnerId="+(parent.getId() == R.id.province)+
//                        ",viewid="+view.getId()+ ",pos="+pos+",id="+id);
//                // ToastUtil.showShort(instance,"选择了["+spinnerItems[pos]+"]");
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