package com.yarinov.filemanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yarinov.filemanagerapp.Audio.AudioListActivity;
import com.yarinov.filemanagerapp.Download.DownloadListActivity;
import com.yarinov.filemanagerapp.Image.ImageListActivity;
import com.yarinov.filemanagerapp.InstallPackage.InstallPackageActivity;
import com.yarinov.filemanagerapp.StorageInfo.StorageInfoActivity;
import com.yarinov.filemanagerapp.Video.VideoListActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    LinearLayout installAppsSection;
    TextView moreStorageInfo;
    ImageView audioSectionIcon, imageSectionIcon, videoSectionIcon, downloadSectionIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkUserPermission();

        installAppsSection = findViewById(R.id.installAppsSection);
        moreStorageInfo = findViewById(R.id.moreStorageInfoText);
        audioSectionIcon = findViewById(R.id.audioSectionIcon);
        imageSectionIcon = findViewById(R.id.imageSectionIcon);
        videoSectionIcon = findViewById(R.id.videoSectionIcon);
        downloadSectionIcon = findViewById(R.id.downloadSectionIcon);

        //Open the installed apps section
        videoSectionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VideoListActivity.class);
                startActivity(intent);
            }
        });

        //Open the installed apps section
        downloadSectionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DownloadListActivity.class);
                startActivity(intent);
            }
        });


        //Open the installed apps section
        installAppsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InstallPackageActivity.class);
                startActivity(intent);
            }
        });


        //Open the Storage info section
        moreStorageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StorageInfoActivity.class);
                startActivity(intent);
            }
        });


        //Open the Storage info section
        audioSectionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AudioListActivity.class);
                startActivity(intent);
            }
        });


        //Open the Storage info section
        imageSectionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
                startActivity(intent);
            }
        });

        setPhoneSpaceText();
    }


    public void setPhoneSpaceText(){
        //Get and set the free space in phone

        TextView freeSpace = findViewById(R.id.availbleSizeText);

        double totalSize = new File(getApplicationContext().getFilesDir().getAbsoluteFile().toString()).getTotalSpace();
        double totMb = totalSize / (1024 * 1024 * 1024);

        double availableSize = new File(getApplicationContext().getFilesDir().getAbsoluteFile().toString()).getFreeSpace();
        double freeMb = availableSize/ (1024 * 1024 * 1024);
        System.out.println("Available MB : "+freeMb);

        freeSpace.setText("Available " + (int)freeMb + " GB / " + (int)totMb + " GB");
    }

    private void checkUserPermission(){
        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},123);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    checkUserPermission();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }

    }
}
