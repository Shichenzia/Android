package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.util.HttpUtil;
import com.example.util.Student;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SteStudentActivity extends AppCompatActivity {
    private EditText s_studentidEdt;
    private EditText s_nameEdt;
    private EditText s_sexEdt;
    private EditText s_ageEdt;
    private Button setBtn;


    private  Integer s_studentid;
    private  String  s_name;
    private  String  s_sex;
    private  Integer  s_age;
    private  Integer s_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ste_student);
        s_studentidEdt =  findViewById(R.id.s_studentid);
        s_nameEdt =  findViewById(R.id.s_name);
        s_sexEdt =  findViewById(R.id.s_sex);
        s_ageEdt =  findViewById(R.id.s_age);
        setBtn = findViewById(R.id.set);

        Intent intent = getIntent();
        s_id = intent.getIntExtra("s_id",0);
        Log.d("TAG", "onCreate: "+s_id);

        Student student = new Student();
        student.setS_id(s_id);

        String address = "http://10.0.2.2:8080/findStudentById";
        HttpUtil.sendOkHttpRequestFindStudentByID(address, student, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TAG", "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responText = response.body().string();
                Log.d("TAG", "onResponse: "+responText);
                try {
                    JSONObject studentObject = new JSONObject(responText);
                    s_sexEdt.setText((studentObject.getString("s_sex")));
                    s_nameEdt.setText(studentObject.getString("s_name"));
                    s_studentidEdt.setText(String.valueOf(studentObject.getInt("s_studentid")));
                    s_ageEdt.setText(String.valueOf(studentObject.getInt("s_age")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student1 = new Student();
                Log.d("TAG", "onClick: "+s_id);
                student1.setS_studentid(Integer.valueOf(String.valueOf(s_studentidEdt.getText())));
                s_name = String.valueOf(s_nameEdt.getText());
                student1.setS_name(s_name);
                student1.setS_sex(String.valueOf(s_sexEdt.getText()));
                student1.setS_age(Integer.valueOf(String.valueOf(s_ageEdt.getText())));
                student1.setS_id(s_id);


                String address = "http://10.0.2.2:8080/updateStudent";
                HttpUtil.sendOkHttpRequestUpdataStudent(address, student1, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("TAG", "请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responText = response.body().string();
                        Log.d("TAG", "onResponse: "+responText);
                        if(responText.equals("1")){
                            Looper.prepare();
                            // 跳转主页
                            Intent intent = new Intent(SteStudentActivity.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(SteStudentActivity.this,"修改成功！",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }else {
                            Looper.prepare();
                            // 跳转主页
                            Intent intent = new Intent(SteStudentActivity.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(SteStudentActivity.this,"修改失败！",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }


                    }
                });


            }
        });









//        WebView webView = (WebView)findViewById(R.id.webview);
//        String url = "http://10.0.2.2:8080/findStudentById?s_id="+s_id;
//        //此方法可以在webview中打开链接而不会跳转到外部浏览器
//        webView.setWebViewClient(new WebViewClient());
//        //此方法可以启用html5页面的javascript
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl(url);




    }
}