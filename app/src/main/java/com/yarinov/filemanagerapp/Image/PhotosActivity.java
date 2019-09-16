package com.yarinov.filemanagerapp.Image;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.yarinov.filemanagerapp.R;

public class PhotosActivity extends AppCompatActivity {

    int int_position;
    private GridView gridView;
    GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        gridView = (GridView)findViewById(R.id.imageGrid);
        int_position = getIntent().getIntExtra("value", 0);
        adapter = new GridViewAdapter(this,ImageListActivity.al_images,int_position);
        gridView.setAdapter(adapter);
    }
}
