package com.example.sms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.StudentAdapter;
import com.example.util.Admin;
import com.example.util.HttpUtil;
import com.example.util.Student;
import com.example.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class StudentFragment extends Fragment  {

    private List<Student> dataList;
    private StudentAdapter adapter;
    private int s_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDataLista();

        // 暂停500ms 等待请求执行完成
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        View view = inflater.inflate(R.layout.fragment_student, container, false);
        //获取lv 并设置适配器
        ListView listView = (ListView) view.findViewById(R.id.studentList);
        //创建适配器，传递数据集合，以及条目中被点击控件的的点击监听
        adapter = new StudentAdapter(getActivity(), dataList);
        listView.setAdapter(adapter);

        adapter.setBottomBtnOnclick(new StudentAdapter.BottomBtnOnclick() {
            @Override
            public void click(View view, int index) {

                switch (view.getId()){
                    case R.id.delete:
                        final int position = index; //获取被点击的控件所在item 的位置，setTag 存储的object，所以此处要强转
                        Student student = dataList.get(position);
                        //点击删除按钮之后，给出dialog提示
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("确认删除"+student.getS_name() + "同学 !");
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataList.remove(position);
                                adapter.notifyDataSetChanged();
                                String address = "http://10.0.2.2:8080/deleteStudent";
                                HttpUtil.sendOkHttpRequestDelStudent(address, student, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e)  {
                                        Log.d("TAG", "请求失败");
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String responText = response.body().string();
                                        if(responText.equals("1")){
                                            Looper.prepare();
                                            Toast.makeText(getActivity(),"删除成功！",Toast.LENGTH_LONG).show();
                                            Looper.loop();
                                        }else {
                                            Looper.prepare();
                                            Toast.makeText(getActivity(),"删除失败！",Toast.LENGTH_LONG).show();
                                            Looper.loop();
                                        }
                                    }
                                });
                            }
                        });
                        builder.show();
                        break;
                    case R.id.add:
                        // 跳转到对应的更改页面
                        student = dataList.get(index);
                        Intent intent = new Intent(getActivity(),SteStudentActivity.class);
                        intent.putExtra("s_id",student.getS_id());
                        startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }


    public void setDataLista(){
        //初始化数据
        dataList = new ArrayList<Student>();
        Student student = new Student(0,"",0);
        // 点击登录请求
        String address = "http://10.0.2.2:8080/findStudent";
        HttpUtil.sendOkHttpRequestStudent(address,student,1,new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                //解决在子线程中调用Toast的异常情况处理
                Looper.prepare();
                Toast.makeText(getActivity(),"获取学生数据失败！",Toast.LENGTH_LONG).show();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responText = response.body().string();
                boolean result = false;
                result = Utility.handLeStudentResponse(responText);
                if(result){
                    dataList = DataSupport.findAll(Student.class);
                }else {

                }
            }
        });


    }



}