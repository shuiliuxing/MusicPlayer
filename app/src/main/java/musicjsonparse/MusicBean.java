package musicjsonparse;

import java.util.List;

/**
 * Created by 30781 on 2016/12/29.
 */
public class MusicBean {

    private List<Songs> songs;
    private Equalizers equalizers;
    private int code;

    public void setSongs(List<Songs> songs){this.songs=songs;}
    public List<Songs> getSongs(){return songs;}

    public void setEqualizers(Equalizers equalizers){this.equalizers=equalizers;}
    public Equalizers getEqualizers(){return equalizers;}

    public void setCode(int code)
    {
        this.code=code;
    }
    public int getCode() {return code;}


}
