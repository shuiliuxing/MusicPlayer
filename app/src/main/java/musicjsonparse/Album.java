package musicjsonparse;

import java.util.List;

/**
 * Created by 30781 on 2016/12/29.
 */
public class Album {
    private String name;
    private int id;
    private String type;
    private int size;
    private long picId;
    private String blurPicUrl;
    private int companyId;
    private long pic;
    private String picUrl;
    private long publishTime;
    private String description;
    private String tags;
    private String company;
    private String briefDesc;

    private Artist artist;
    private List<Song> songs;
    private List<String> alias;

    private int status;
    private int copyrightId;
    private String commentThreadId;

    private List<Artists> artists;

    public void setPicUrl(String picUrl){this.picUrl=picUrl;}
    public String getPicUrl(){return picUrl;}

}
