package com.example.andronews;

public class news {

    private String date;
    private String title;
    private String tag;
    private String url;

    public news(String date,String title,String tag,String url){
        this.date=date;
        this.title=title;
        this.tag=tag;
        this.url=url;
    }


    public String getDate(){
        return date;
    }

    public String getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl(){
        return url;
    }
}
