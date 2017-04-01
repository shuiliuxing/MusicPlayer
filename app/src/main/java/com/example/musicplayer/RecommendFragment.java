package com.example.musicplayer;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import musicjsonparse.MusicBean;

/**
 * Created by 30781 on 2017/1/3.
 */
public class RecommendFragment extends Fragment implements OnClickListener{

    private TextView tvRecommend;
    private TextView tvList;
    private TextView tvMv;
    private TextView tvMine;

    private ListView llMusic;
    private List<Music> listAll;
    private MusicAdapter adapter;

    private ImageView ivPlayImage;
    private TextView tvPlayName;
    private TextView tvPlayAuthor;

    private static final int LOAD_SUCCESS = 1; // 加载成功
    private static final int SERCH_SUCCESS = 2; // 加载失败
    private static final int PLAY_SUCCESS=3;  //成功加载

    //private SearchView svSearch;

    private MediaPlayer mediaPlayer=new MediaPlayer();
    private ImageView ivPre;
    private ImageView ivPlay;
    private ImageView ivNext;
    private int selectNum;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_SUCCESS:
                    adapter.notifyDataSetChanged();
                    break;
                case SERCH_SUCCESS:
                    adapter.notifyDataSetChanged();
                    selectNum=listAll.size()-1;
                    PlayMusic(listAll.get(selectNum).getLink());
                    ivPlayImage.setImageBitmap(listAll.get(selectNum).getImage());
                    tvPlayName.setText(listAll.get(selectNum).getName());
                    tvPlayAuthor.setText(listAll.get(selectNum).getAuthor());
                    ivPlay.setImageResource(R.drawable.play);
                    break;
                case PLAY_SUCCESS:
                    try {
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    break;
            }

        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.recommend_layout,container,false);


        llMusic=(ListView)view.findViewById(R.id.ll_music);
        listAll=new ArrayList<Music>();
        adapter=new MusicAdapter(getActivity(),R.layout.music_item,listAll);
        llMusic.setAdapter(adapter);

        getPicture();  //获取图片

        ivPlayImage=(ImageView)view.findViewById(R.id.tv_playimage);
        tvPlayName=(TextView)view.findViewById(R.id.tv_playname);
        tvPlayAuthor=(TextView)view.findViewById(R.id.tv_playauthor);

        ivPre=(ImageView)view.findViewById(R.id.iv_pre);
        ivPre.setOnClickListener(this);
        ivPlay=(ImageView)view.findViewById(R.id.iv_play);
        ivPlay.setOnClickListener(this);
        ivNext=(ImageView)view.findViewById(R.id.iv_next);
        ivNext.setOnClickListener(this);

        llMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectNum = position;
                ivPlayImage.setImageBitmap(listAll.get(position).getImage());
                tvPlayName.setText(listAll.get(position).getName());
                tvPlayAuthor.setText(listAll.get(position).getAuthor());

                ivPlay.setImageResource(R.drawable.play);
                PlayMusic(listAll.get(position).getLink());
            }
        });

        return view;


    }


    //下载图片的主方法
    private void getPicture() {
        int[] data={448317723,28285910,29729123,28639182,27901533,
                368727,444267928,167942,20435748,5281022,
                447925710,32007838,28892522,448144319,446875807};
        for(int i=0;i<data.length;i++) {
            final String path = new String("http://music.163.com/api/song/detail/?id=" + data[i] + "&ids=%5B" + data[i] + "%5D&csrf_token");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection connection = null;
                    try {
                        URL url = new URL(path);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);
                        connection.setDoInput(true);
                        connection.setDoOutput(true);

                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null)
                            sb.append(line);
                        in.close();

                        Gson gson = new Gson();
                        MusicBean musicBean = gson.fromJson(sb.toString(), MusicBean.class);

                        URL musicUrl = new URL(musicBean.getSongs().get(0).getAlbum().getPicUrl());
                        InputStream is = musicUrl.openStream();  //打开url对应的资源输入流
                        Bitmap bitmap = BitmapFactory.decodeStream(is);   //从InputStream中解析出图片
                        is.close();

                        String name = musicBean.getSongs().get(0).getName();
                        String author = musicBean.getSongs().get(0).getArtists().get(0).getName();
                        String link = musicBean.getSongs().get(0).getMp3Url();

                        Music music = new Music(bitmap, name, author, link);
                        listAll.add(music);

                        handler.sendEmptyMessage(LOAD_SUCCESS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (connection != null)
                            connection.disconnect();
                    }
                }
            }).start();
        }
    }

    //播放音乐
    private void PlayMusic(final String path)
    {
        mediaPlayer.stop();
        mediaPlayer.reset();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer.setDataSource(path);
                    handler.sendEmptyMessage(PLAY_SUCCESS);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.iv_pre:
                --selectNum;
                if(selectNum>=0) {
                    PlayMusic(listAll.get(selectNum).getLink());
                    ivPlayImage.setImageBitmap(listAll.get(selectNum).getImage());
                    tvPlayName.setText(listAll.get(selectNum).getName());
                    tvPlayAuthor.setText(listAll.get(selectNum).getAuthor());
                    ivPlay.setImageResource(R.drawable.play);
                }
                break;
            case R.id.iv_play:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    ivPlay.setImageResource(R.drawable.pause);
                }
                else {
                    mediaPlayer.start();
                    ivPlay.setImageResource(R.drawable.play);
                }
                break;
            case R.id.iv_next:
                ++selectNum;
                if(selectNum<=16) {
                    PlayMusic(listAll.get(selectNum).getLink());
                    ivPlayImage.setImageBitmap(listAll.get(selectNum).getImage());
                    tvPlayName.setText(listAll.get(selectNum).getName());
                    tvPlayAuthor.setText(listAll.get(selectNum).getAuthor());
                    ivPlay.setImageResource(R.drawable.play);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

}
