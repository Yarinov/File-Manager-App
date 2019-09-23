package com.yarinov.filemanagerapp.Image;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.yarinov.filemanagerapp.R;

import net.alhazmy13.mediagallery.library.views.MediaGalleryView;

import java.io.File;
import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {


    ArrayList<String> listOfAllImages = new ArrayList<String>();

    private ImageView imageFile;

    private GridView imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        imageList = findViewById(R.id.imageGrid);

        //Set the custom adapter to the list view
        CustomAdapter appCustomAdapter = new CustomAdapter(this);

        imageList.setAdapter(appCustomAdapter);



    }

    private void getImages() {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(absolutePathOfImage);
        }

    }

    class CustomAdapter extends BaseAdapter {

        private Activity context;

        public CustomAdapter(Activity localContext) {
            context = localContext;
           getImages();
        }

        @Override
        public int getCount() {
            //Return the size of arraylist -> The amount of installed apps
            return listOfAllImages.size();
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
            view = getLayoutInflater().inflate(R.layout.custom_images_list, null);

            imageFile = view.findViewById(R.id.GalleryPreviewImg);

            File imgFile = new  File(listOfAllImages.get(i));
//
//            if(imgFile.exists()){
//
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//                imageFile.setImageBitmap(myBitmap);
//
//            }

            Picasso.with(context).load(imgFile).placeholder(R.drawable.android_ic)
                    .resize(450, 350).centerCrop().into(imageFile);

            return view;
        }
    }




}

