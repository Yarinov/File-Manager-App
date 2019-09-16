package com.yarinov.filemanagerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yarinov.filemanagerapp.Audio.AudioListActivity;
import com.yarinov.filemanagerapp.InstallPackage.InstallPackageActivity;
import com.yarinov.filemanagerapp.StorageInfo.StorageInfoActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    LinearLayout installAppsSection;
    TextView moreStorageInfo;
    ImageView audioSectionIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        installAppsSection = findViewById(R.id.installAppsSection);
        moreStorageInfo = findViewById(R.id.moreStorageInfoText);
        audioSectionIcon = findViewById(R.id.audioSectionIcon);


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
}
