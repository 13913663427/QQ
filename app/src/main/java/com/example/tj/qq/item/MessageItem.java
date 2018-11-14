package com.example.tj.qq.item;

//定义数据源数据类型
public class MessageItem {

    private String imageUrl;
    private String tittle;
    private String content;
    private long time;// 1541429580161


    public MessageItem(String imageUrl, String tittle, String content, long time) {
        this.imageUrl = imageUrl;
        this.tittle = tittle;
        this.content = content;
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
