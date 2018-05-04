package com.example.androidproj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 思超 on 2016/12/8.
 */

public class login extends Activity {
    private Button logbtn,regbtn;
    private EditText act, pwd;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // 获取控件
        act = (EditText) findViewById(R.id.account2);
        pwd = (EditText) findViewById(R.id.password2);
        logbtn=(Button)findViewById(R.id.button2);
        regbtn=(Button)findViewById(R.id.button3);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkNetwork()) {
                    Toast toast = Toast.makeText(login.this, "网络未连接", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                // 提示框
                else {
                    dialog = new ProgressDialog(login.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("正在登陆，请稍后...");
                    dialog.setCancelable(false);
                    dialog.show();
                    new login.HttpThread1(act.getText().toString().trim(), pwd.getText().toString().trim()).start();
                }
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regint=new Intent(login.this,register.class);
                startActivity(regint);
            }
        });

    }


    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(login.this,course.class);
                    startActivity(intent);
                    break;
                case 1:
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(),"登陆失败",Toast.LENGTH_SHORT).show();
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

        public HttpThread1(String act, String pwd) {
            this.act = act;
            this.pwd = pwd;
        }

        private String doGet() throws IOException {
            //InputStream is = null;
            String result="";
            String path = "http://192.168.137.1:8080/tomcat/login1.htm";
            path = path + "?act=" + act + "&pw=" + pwd;
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(5000);

                /*if(conn.getResponseCode()==200)
                {
                    is=conn.getInputStream();
                    System.out.println(parseInfo(is));
                    return parseInfo(is);
                }*/
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String str;
                StringBuffer sb = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }

                result=sb.toString();
                System.out.println(result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        private String parseInfo(InputStream inStream) throws Exception {
            byte[] data = read(inStream);
            // 转化为字符串
            return new String(data, "UTF-8");
        }

        public byte[] read(InputStream inStream) throws Exception {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            inStream.close();
            return outputStream.toByteArray();
        }

        @Override
        public void run() {
            String result;
            result=null;
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
           // handler.sendEmptyMessage(0);
        }
    }
}