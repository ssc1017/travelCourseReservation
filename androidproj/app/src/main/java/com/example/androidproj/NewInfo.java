package com.example.androidproj;

/**
 * Created by 思超 on 2016/12/10.
 */

public class NewInfo {
    private String title;
    private String detail;
    private String type;
    private String imageUrl;

    public NewInfo(String title,String detail,String type,String imageUrl)
    {
        super();
        this.title=title;
        this.detail=detail;
        this.type=type;
        this.imageUrl=imageUrl;
    }

    public NewInfo()
    {
        super();
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getDetail(){
        return detail;
    }

    public void setDetail(String detail){
        this.detail=detail;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type=type;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl=imageUrl;
    }
}
