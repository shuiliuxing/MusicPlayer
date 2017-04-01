package musicparse;

/**
 * Created by 30781 on 2016/12/29.
 */
public class Album {
    private int id;
    private String name;
    private Artist artist;
    private String picUrl;

    public void setPicUrl(String picUrl){this.picUrl=picUrl;}
    public String getPicUrl(){return picUrl;}
}
