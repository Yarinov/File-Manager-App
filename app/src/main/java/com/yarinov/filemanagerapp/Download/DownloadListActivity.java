package com.yarinov.filemanagerapp.Download;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yarinov.filemanagerapp.R;

import java.io.File;
import java.util.ArrayList;

public class DownloadListActivity extends AppCompatActivity {

    ListView downloadsItemList;

    private ArrayList<DownloadItemInfo> downloads = new ArrayList<DownloadItemInfo>();

    private TextView downloadFileName, downloadFileDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_list);


        downloadsItemList = findViewById(R.id.downloadsItemList);

        //Set the custom adapter to the list view
        CustomAdapter appCustomAdapter = new CustomAdapter();

        downloadsItemList.setAdapter(appCustomAdapter);

        loadDownloads();


    }

    private void loadDownloads(){
        File downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
       // downloads = downloadPath.list();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC+"!=0";
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{

                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));

                    DownloadItemInfo d = new DownloadItemInfo(name,artist,url);
                    downloads.add(d);

                }while (cursor.moveToNext());
            }

            cursor.close();


        }
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //Return the size of arraylist -> The amount of installed apps
            return downloads.size();
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
            view = getLayoutInflater().inflate(R.layout.custom_audio_list, null);

            DownloadItemInfo downloadFile = downloads.get(i);

            downloadFileName = view.findViewById(R.id.audioNameInList);
            downloadFileDet = view.findViewById(R.id.audioDetInList);

            //Set the icon and app name
            downloadFileName.setText(downloadFile.getItemName());
            downloadFileDet.setText(downloadFile.getItemSize());


            return view;
        }
    }
}
