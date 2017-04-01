package musicparse;

import java.util.List;

import musicjsonparse.Album;

/**
 * Created by 30781 on 2016/12/29.
 */
public class Songs {
    private int id;
    private String name;
    private List<Artists> artists;
    private Album album;
    private String audio;
    private int djProgramId;
    private String page;

    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public void setArtists(List<Artists> artists){this.artists=artists;}
    public List<Artists> getArtists(){return artists;}


    public void setAudio(String audio){this.audio=audio;}
    public String getAudio(){return audio;}

    public void setAlbum(Album album){this.album=album;}
    public Album getAlbum(){return album;}
}
