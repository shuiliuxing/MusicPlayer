package com.example.musicplayer;

import android.graphics.Bitmap;
/**
 * Created by 30781 on 2016/12/31.
 */
public class Music {
    private Bitmap image;
    private String name;
    private String author;
    private String link;

    public Music(Bitmap image,String name,String author,String link)
    {
        this.image=image;
        this.name=name;
        this.author=author;
        this.link=link;
    }


    public void setImage(Bitmap image){this.image=image;}
    public Bitmap getImage(){return image;}

    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public void setAuthor(String author){this.author=author;}
    public String getAuthor(){return author;}

    public void setLink(String link){this.link=link;}
    public String getLink(){return link;}
}
