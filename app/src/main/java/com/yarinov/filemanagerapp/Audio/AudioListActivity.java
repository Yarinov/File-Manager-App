package com.yarinov.filemanagerapp.Audio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yarinov.filemanagerapp.R;

import java.util.ArrayList;

public class AudioListActivity extends AppCompatActivity {

    private ArrayList<AudioInfo> _songs = new ArrayList<AudioInfo>();

    private TextView audioFileName, audioFileDet;

    private ListView audioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_list);

        audioList = findViewById(R.id.audioList);

        //Set the custom adapter to the list view
        CustomAdapter appCustomAdapter = new CustomAdapter();

        audioList.setAdapter(appCustomAdapter);

        loadSongs();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void loadSongs(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC+"!=0";
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{

                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    AudioInfo s = new AudioInfo(name,artist,url);
                    _songs.add(s);

                }while (cursor.moveToNext());
            }

            cursor.close();


        }
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //Return the size of arraylist -> The amount of installed apps
            return _songs.size();
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

            AudioInfo audioFile = _songs.get(i);

            audioFileName = view.findViewById(R.id.nameInList);
            audioFileDet = view.findViewById(R.id.detInList);

            //Set the icon and app name
            audioFileName.setText(audioFile.getSongname());
            audioFileDet.setText(audioFile.getArtistname());


            return view;
        }
    }

}
