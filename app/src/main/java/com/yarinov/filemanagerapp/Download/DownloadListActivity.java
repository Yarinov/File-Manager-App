package com.yarinov.filemanagerapp.Download;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yarinov.filemanagerapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadListActivity extends AppCompatActivity {

    ListView downloadsItemList;

    private File[] downloads;
    private TextView downloadFileName, downloadFileDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_list);


        downloadsItemList = findViewById(R.id.downloadsItemList);


        File downloadDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());

        downloads = getFilesFromDir(downloadDir);

        //Set the custom adapter to the list view
        CustomAdapter appCustomAdapter = new CustomAdapter();

        downloadsItemList.setAdapter(appCustomAdapter);
    }

    public File[] getFilesFromDir(File filesFromSD) {

        File listAllFiles[] = filesFromSD.listFiles();

        if (listAllFiles != null && listAllFiles.length > 0) {
            for (File currentFile : listAllFiles) {
                if (currentFile.isDirectory()) {
                    getFilesFromDir(currentFile);
                } else {
                    if (currentFile.getName().endsWith("")) {
                        // File absolute path
                        Log.e("File path", currentFile.getAbsolutePath());
                        // File Name
                        Log.e("File path", currentFile.getName());

                    }
                }
            }
        }

        return listAllFiles;
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //Return the size of arraylist -> The amount of installed apps
            return downloads.length;
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

            File downloadFile = downloads[i];

            downloadFileName = view.findViewById(R.id.nameInList);
            downloadFileDet = view.findViewById(R.id.detInList);

            //Set the icon and app name
            downloadFileName.setText(downloadFile.getName());

            double fileSize = Double.parseDouble(String.valueOf(downloadFile.length()/(1024*1024)));
            downloadFileDet.setText(fileSize + " MB");


            return view;
        }
    }
}
