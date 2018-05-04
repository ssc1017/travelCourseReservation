package com.example.androidproj;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by 思超 on 2016/12/9.
 */


public class course extends Activity {
    private TabHost tabHost;
    private ListView lv;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);
        context=this;
        lv=(ListView)findViewById(R.id.lv);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    getcoursexml();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<NewInfo> newInfoList=null;
                    try
                    {
                       newInfoList=getNewsFromInternet();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Message msg=new Message();
                    if(newInfoList!=null){
                        msg.what=0;
                        msg.obj=newInfoList;
                    }else {
                        msg.what =1;
                    }
                handler.sendMessage(msg);
            }
        }).start();

        tabHost=(TabHost)findViewById(R.id.activity_main);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("全部线路").setContent(R.id.LinearLayout));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("国内线路").setContent(R.id.LinearLayout2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("国际线路").setContent(R.id.LinearLayout3));
    }

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            List<NewInfo> newInfoList;
            switch (msg.what){
                case 0:
                    newInfoList=(List<NewInfo>)msg.obj;
                    MyAdapter adapter=new MyAdapter(context);
                    adapter.setList(newInfoList);
                    lv.setAdapter(adapter);
                    break;
                case 1:
                    Toast.makeText(course.this,"无法获取服务器信息",Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    private List<NewInfo> getNewsFromInternet() throws IOException{
        String path = "http://192.168.137.1:8080/tomcat/course.xml";
        System.out.println(path);
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            if(conn.getResponseCode()==200)
            {
                InputStream is=conn.getInputStream();
                List<NewInfo> newInfoList=getNewListFromInputStream(is);
                return newInfoList;
            }else{
                Log.i(TAG,"访问失败："+conn.getResponseCode());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private List<NewInfo> getNewListFromInputStream(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "gb2312");
        int eventType = parser.getEventType();
        List<NewInfo> newInfoList = new ArrayList<NewInfo>();
        NewInfo newInfo = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if ("course".equals(tagName)) {
                        newInfo = new NewInfo();
                    } else if ("title".equals(tagName)) {
                        newInfo.setTitle(parser.nextText());
                    } else if ("detail".equals(tagName)) {
                        newInfo.setDetail(parser.nextText());
                    } else if ("type".equals(tagName)) {
                        newInfo.setType(parser.nextText());
                    } else if ("image".equals(tagName)) {
                        newInfo.setImageUrl(parser.nextText());
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("course".equals(tagName)) {
                        newInfoList.add(newInfo);
                    }
                    break;
                default:
                    break;
            }
            eventType = parser.next();
        }
        return newInfoList;
    }

    private void getcoursexml ()throws IOException{
        String result = "";
        String path = "http://192.168.137.1:8080/tomcat/coursexml.htm";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }

            result = sb.toString();
            System.out.println(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}