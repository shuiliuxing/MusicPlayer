package musicparse;

import java.util.List;

/**
 * Created by 30781 on 2016/12/29.
 */
public class Result {
    private int songCount;
    private List<Songs> songs;

    public void setSongs(List<Songs> songs){this.songs=songs;}
    public List<Songs> getSongs(){return songs;}

}
