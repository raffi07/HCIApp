package com.example.teachin;

public class LetterListItem {
    private int mImageResource;
    private String title;
    private String subtitle;

    public LetterListItem(int mImageResource, String title, String subtitle){
        this.mImageResource = mImageResource;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getImageResource(){
        return this.mImageResource;
    }

    public String getTitle(){
        return this.title;
    }

    public String getSubtitle(){
        return this.subtitle;
    }
}
