package com.example.androidproj;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class register extends AppCompatActivity {
    private Button regbtn;
    private EditText act, pwd, name, age, tele, mail;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // 获取控件
        act = (EditText) findViewById(R.id.account);
        pwd = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        tele = (EditText) findViewById(R.id.tele);
        mail = (EditText) findViewById(R.id.mail);
        regbtn = (Button) findViewById(R.id.button);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                // 检测网络，无法检测wifi
                if (!checkNetwork()) {
                    Toast toast = Toast.makeText(register.this, "网络未连接", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                // 提示框
                else {
                    dialog = new ProgressDialog(register.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("正在注册，请稍后...");
                    dialog.setCancelable(false);
                    dialog.show();
                    new HttpThread1(act.getText().toString().trim(), pwd.getText().toString().trim(), name.getText().toString().trim(), radioButton.getText().toString(), age.getText().toString().trim(), tele.getText().toString().trim(), mail.getText().toString().trim()).start();
                }
            }
        });

    }


    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(register.this,login.class);
                    startActivity(intent);
                    break;
                case 1:
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    // 检测网络
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    public class HttpThread1 extends Thread {
        String act;
        String pwd;
        String name;
        String gender;
        String age;
        String tele;
        String mail;

        public HttpThread1(String act, String pwd, String name, String gender, String age, String tele, String mail) {
            this.act = act;
            this.pwd = pwd;
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.tele = tele;
            this.mail = mail;
        }

        private String doGet() throws IOException {
            String path = "http://192.168.137.1:8080/tomcat/register.htm";
            String result="";
            //path = path + "?act=" + act + "&pw=" + pwd + "&name=" + name + "&gender=" + gender + "&age=" + age + "&tele=" + tele + "&mail=" + mail;
            System.out.println(path);
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Charset", "UTF-8");
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);//post方式不能设置缓存，需手动设置为false
                String data="act=" + URLEncoder.encode(act,"UTF-8") + "&pw=" + URLEncoder.encode(pwd,"UTF-8") + "&name=" + URLEncoder.encode(name,"UTF-8") + "&gender=" + URLEncoder.encode(gender,"UTF-8") + "&age=" + URLEncoder.encode(age,"UTF-8") + "&tele=" + URLEncoder.encode(tele,"UTF-8") + "&mail=" + URLEncoder.encode(mail,"UTF-8");
                OutputStream out=conn.getOutputStream();
                out.write(data.getBytes());
                out.flush();
                out.close();
                conn.connect();



                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String str;
                StringBuffer sb = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }
                System.out.println("result" + sb.toString());
                result=sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        public void run() {
            String result="";
            try {
                result=doGet();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Message msg=handler.obtainMessage();
            if(result.equals("success")){
                msg.what=0;
                handler.sendMessage(msg);
            }
            else
            if(result.equals("fail")){
                msg.what=1;
                handler.sendMessage(msg);
            }
        }
    }
}
