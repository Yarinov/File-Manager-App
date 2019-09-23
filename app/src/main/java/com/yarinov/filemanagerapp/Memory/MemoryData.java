package com.yarinov.filemanagerapp.Memory;

import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.yarinov.filemanagerapp.Audio.AudioInfo;
import com.yarinov.filemanagerapp.Download.DownloadItemInfo;
import com.yarinov.filemanagerapp.Video.VideoInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MemoryData {

    File[] allFiles;

    Long audioSize, imageSize, videoSize;

    public MemoryData(){
        allFiles = getFilesFromDir(Environment.getExternalStorageDirectory());

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


    }

