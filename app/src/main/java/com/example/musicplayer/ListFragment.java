package com.example.musicplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by 30781 on 2017/1/4.
 */
public class ListFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
    {
        View view=inflater.inflate(R.layout.list_layout,container,false);

        return view;
    }
}
