package com.example.androidproj;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by 思超 on 2016/12/10.
 */

public class MyAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<NewInfo> newInfoList;
    private Context context;
    private ImageView imageView;
    private TextView title;
    private TextView detail;
    private TextView type;

    public int getCount(){
        return newInfoList.size();
    }


    public MyAdapter(Context context){
        super();
        this.context=context;
    }

    public void setList(List<NewInfo> newInfoList){
        this.newInfoList=newInfoList;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, final ViewGroup parent){
        View view=null;
        if(convertView==null){
            view=inflater.inflate(R.layout.list,null);
        }
        else view=convertView;
        imageView=(ImageView)view.findViewById(R.id.imageview);
        title=(TextView)view.findViewById(R.id.tv);
        detail=(TextView)view.findViewById(R.id.tv2);
        type=(TextView)view.findViewById(R.id.tv3);

        NewInfo newInfo=newInfoList.get(position);
        imageView.setTag(newInfo);

        new bitmapthread(newInfo,imageView).start();

        title.setText(newInfo.getTitle());
        detail.setText(newInfo.getDetail());
        type.setText(newInfo.getType());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("报名")
                        .setMessage("确定参加这条线路吗？")
                        .setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int which){
                                Toast.makeText(context,"已经报名",Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        Toast.makeText(context,"欢迎选择其他线路",Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });
        return view;
    }

    public Object getItem(int position){
        return newInfoList.get(position);
    }

    public long getItemId(int position){
        return position;
    }
}

