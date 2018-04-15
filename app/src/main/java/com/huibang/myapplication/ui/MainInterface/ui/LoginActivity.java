package com.huibang.myapplication.ui.MainInterface.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.huibang.myapplication.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etLoginNum;
    private EditText etLoginPwd;
    private ImageButton ibLoginVisible;
    private Button btnLogin;
    private int count;
    public static String userid = null;
    public static String userpassword = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private void findViews() {

        etLoginNum = (EditText) findViewById(R.id.et_login_num);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        ibLoginVisible = (ImageButton) findViewById(R.id.ib_login_visible);
        btnLogin = (Button) findViewById(R.id.btn_login);


        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == ibLoginVisible) {
            count++;
            if (count % 2 == 0) {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                etLoginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            }
        } else if (v == btnLogin) {
            userid = etLoginNum.getText().toString().trim();
            userpassword = etLoginPwd.getText().toString().trim();
            if (TextUtils.isEmpty(userid) || TextUtils.isEmpty(userpassword)) {
                Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else {

                Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(2000);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //1.打开支持异步的浏览器，自动在后台开启线程，发送网络请求
                //  String url="http://10.200.97.61:8080/DeliveryWeb/servlet/LoginServlet";
                // doLogin();
            }
        }
    }


//    private void doLogin() {
//        StringRequest request = new StringRequest(ApiServers.DRIVERLOGIN, RequestMethod.POST);
//        request.add("username",userid);
//        request.add("password",userpassword);
//        AsyncRequestExecutor.INSTANCE.execute(0, request, new SimpleResponseListener<String>() {
//            @Override
//            public void onSucceed(int what, Response<String> response) {
//                // 请求成功。
//                String content = response.get();
//                if("login success".equals(content)){
//                    Intent intent = new Intent();
//                    intent.setClass(Login.this,MainActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("userid==", userid);
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                    // startActivity(new Intent(Login.this,MainActivity.class));
//                    //finish();
//                }else if("login failed".equals(content)) {
//                    Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailed(int what, Response<String> response) {
//                // 请求失败。
//                Toast.makeText(Login.this,"请求失败",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
