package com.yarinov.filemanagerapp.Video;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yarinov.filemanagerapp.R;

import java.util.ArrayList;

public class VideoListActivity extends AppCompatActivity {

    private ArrayList<VideoInfo> videos = new ArrayList<VideoInfo>();

    private TextView videoFileName, videoFileDet;

    private ImageView imageInList;

    private ListView videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);


        videoList = findViewById(R.id.videoListVIew);

        //Set the custom adapter to the list view
        CustomAdapter appCustomAdapter = new CustomAdapter();

        videoList.setAdapter(appCustomAdapter);

        loadVideos();

    }

    public void loadVideos() {

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Video.Media.DEFAULT_SORT_ORDER;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{

                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));

                    double videoSize = Double.parseDouble(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE)))
                            / (1024*1024);

                    String videoSizeFormatted = String.format("%.2f", videoSize);

                    VideoInfo s = new VideoInfo(name, videoSizeFormatted);
                    videos.add(s);

                }while (cursor.moveToNext());
            }

            cursor.close();


        }
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //Return the size of arraylist -> The amount of installed apps
            return videos.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //Set the custom layout as the current view
            view = getLayoutInflater().inflate(R.layout.custom_items_list, null);

            VideoInfo videoFile = videos.get(i);

            videoFileName = view.findViewById(R.id.nameInList);
            videoFileDet = view.findViewById(R.id.detInList);
            imageInList = view.findViewById(R.id.ImageInList);

            //Set the icon and app name
            videoFileName.setText(videoFile.getVideoName());
            videoFileDet.setText(videoFile.getVideoSize() + " MB");
            imageInList.setImageResource(R.drawable.video_player_icon);

            return view;
        }
    }
}
