package com.example.parentin;

public class CardItem {
    private int mImageResource;
    private int background;
    private String title;
    private String subtitle;

    public CardItem(int mImageResource, int background, String title, String subtitle){
        this.mImageResource = mImageResource;
        this.background = background;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getImageResource(){
        return this.mImageResource;
    }

    public int getBackground(){return this.background;}

    public String getTitle(){
        return this.title;
    }

    public String getSubtitle(){
        return this.subtitle;
    }
}