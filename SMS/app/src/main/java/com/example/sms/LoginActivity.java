package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.util.Admin;
import com.example.util.HttpUtil;
import com.example.util.Utility;
import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdt;
    private EditText passwordEdt;
    private CheckBox rememberChB;
    private Button loginBtn;
    private String a_username;
    private String a_password;
    private boolean isRemeber = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 判断数据库是否有用户数据，有则不用登录
        List<Admin> adminList = DataSupport.findAll(Admin.class);
        if(adminList.size()>0){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
            startActivity(intent);
        }

        usernameEdt = findViewById(R.id.username);
        passwordEdt = findViewById(R.id.password);
        rememberChB = findViewById(R.id.remember);
        loginBtn = findViewById(R.id.login);

        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        a_username = pref.getString("a_username","");
        a_password = pref.getString("a_password","");

        // 判断是否有记住保存的密码
        if(!a_username.equals("") && !a_password.equals("")){
            usernameEdt.setText(a_username);
            passwordEdt.setText(a_password);
            rememberChB.setChecked(true);
        }else {

        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a_username = usernameEdt.getText().toString();
                a_password = passwordEdt.getText().toString();
                isRemeber = rememberChB.isChecked();

                // 判断账号密码是否为空
                if(a_password.equals("")|| a_username.equals("")){
                    Toast.makeText(LoginActivity.this,"请输入账号密码！！",Toast.LENGTH_LONG).show();
                }else {
                    //封装成对象
                    Admin admin = new Admin();
                    admin.setA_username(a_username);
                    admin.setA_password(a_password);

                    // 点击登录请求
                    String address = "http://10.0.2.2:8080/login";
                    HttpUtil.sendOkHttpRequestLogin(address, admin,new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.d("TAG", "请求失败");
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responText = response.body().string();
                            boolean result = false;
                            result = Utility.handLeAdminResponse(responText);
                            if(result){
                                //解决在子线程中调用Toast的异常情况处理
                                Looper.prepare();
                                setRemember();
                                // 跳转主页
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_LONG).show();
                                startActivity(intent);
                                Looper.loop();
                            }else {
                                //解决在子线程中调用Toast的异常情况处理
                                Looper.prepare();
                                Toast.makeText(LoginActivity.this,"账号，密码错误！",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }
                        }
                    });
                }
            }
        });
    }

    // 记住密码
    private void setRemember(){
        // 选中记住密码
        if(isRemeber){
            SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
            editor.putString("a_username",a_username);
            editor.putString("a_password",a_password);
            editor.apply();
        }
    }
}