package com.example.musicplayer;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by 30781 on 2016/12/31.
 */
public class MusicAdapter extends ArrayAdapter<Music>{
    private int resourceId;
    public MusicAdapter(Context context,int textViewResourceId,List<Music> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position,View contentView,ViewGroup parent)
    {
        Music music=getItem(position);

        View view;
        MusicHolder musicHolder;
        if(contentView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

            musicHolder = new MusicHolder();
            musicHolder.musicImage = (ImageView) view.findViewById(R.id.iv_image);
            musicHolder.musicName = (TextView) view.findViewById(R.id.tv_name);
            musicHolder.musicAuthor = (TextView) view.findViewById(R.id.tv_author);

            view.setTag(musicHolder);
        }
        else
        {
            view=contentView;
            musicHolder=(MusicHolder)view.getTag();
        }
        musicHolder.musicImage.setImageBitmap(music.getImage());
        musicHolder.musicName.setText(music.getName());
        musicHolder.musicAuthor.setText(music.getAuthor());
        return view;
    }

    class MusicHolder
    {
        ImageView musicImage;
        TextView musicName;
        TextView musicAuthor;
    }
}
