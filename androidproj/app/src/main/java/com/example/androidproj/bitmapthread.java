package com.example.androidproj;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 思超 on 2016/12/11.
 */

public class bitmapthread extends Thread{
    private ImageView imageView;
    private NewInfo newInfo;

    public bitmapthread(NewInfo newInfo,ImageView imageView){
        this.newInfo=newInfo;
        this.imageView=imageView;
    }

    private Handler handler=new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    Bitmap bitmap=(Bitmap) msg.obj;
                    if(imageView.getTag()==newInfo)
                    {imageView.setImageBitmap(bitmap);}
                    break;
                case 1:
                    break;
                default:
                    break;
            }
        }
    };

    private Bitmap getbitmap(String path,ImageView imageView) throws IOException
    {
        Bitmap bitmap=null;
        try{
            URL url = new URL(path);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            InputStream is=conn.getInputStream();
            bitmap= BitmapFactory.decodeStream(is);
            is.close();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void run() {
        Bitmap bitmap=null;
        try
        {
            bitmap=getbitmap(newInfo.getImageUrl(),imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
        Message msg=new Message();
        if(bitmap!=null){
            msg.what=0;
            msg.obj=bitmap;
        }else {
            msg.what =1;
        }
        handler.sendMessage(msg);
    }
}