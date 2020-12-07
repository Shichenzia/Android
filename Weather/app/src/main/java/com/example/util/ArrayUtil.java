package com.example.util;

import android.util.Log;

public class ArrayUtil {

    // 获取数组长度
    public int countNum(String[] str){
        int count=0;
        if(str != null){
            for (int i=0;i<str.length;i++){
                if(!"".equals(str[i]))
                    count++;
            }
        }
        return count;
    }

    // 尾部添加数据
    public String[] push(String[] str, String element){
        int n = countNum(str) +1 ;
        String [] newStr = new String[n];
        if(str != null){
            for(int i=0;i<str.length;i++){
                newStr[i] = str[i];
            }
        }
        newStr[n-1] = element;
        return newStr;
    }

//    // 清空数组
//    public String[] clear(){
//        return
//    }

    // 自定义toString
    public void arrayPlay(String[] str){
        String x="";
        if(str != null){
            for(int i=0;i<str.length;i++){
                if(!"".equals(str[i]))
                    x = x+str[i];
            }
        }

        Log.d("TAG",x);
    }
}
