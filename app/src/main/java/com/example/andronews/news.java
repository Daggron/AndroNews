package com.example.andronews;

public class news {

    private String date;
    private String title;
    private String tag;
    private String url;
    private String author;

    public news(String author,String date,String title,String tag,String url){
        this.date=date;
        this.title=title;
        this.tag=tag;
        this.url=url;
        this.author=author;
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

    public String getAuthor() {
        return author;
    }
}
