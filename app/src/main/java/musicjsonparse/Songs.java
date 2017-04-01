package musicjsonparse;

import java.util.List;

/**
 * Created by 30781 on 2016/12/29.
 */
public class Songs {
    private String name;
    private int id;
    private int position;
    private List<String> alias;
    private int status;
    private int fee;
    private int copyrightId;
    private String disc;
    private int no;

    private List<Artists> artists;
    private Album album;

    private boolean starred;
    private int popularity;
    private int score;
    private int starredNum;
    private int duration;
    private int playedNum;
    private int dayPlays;
    private int hearTime;
    private String ringtone;
    private String crbt;
    private String audition;
    private String copyFrom;
    private String commentThreadId;
    private String rtUrl;
    private int ftype;

    private List<RtUrls> rtUrls;
    private int copyright;
    private int mvid;

    private BMusic bMusic;
    private String mp3Url;
    private int rtype;
    private String rurl;
    private HMusic hMusic;
    private MMusic mMusic;
    private LMusic lMusic;


    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public void setId(int id){this.id=id;}
    public int getId(){return id;}

    public void setArtists(List<Artists> artists){this.artists=artists;}
    public List<Artists> getArtists(){return artists;}

    public void setAlbum(Album album){this.album=album;}
    public Album getAlbum(){return album;}

    public void setMp3Url(String mp3Url){this.mp3Url=mp3Url;}
    public String getMp3Url(){return mp3Url;}
}
