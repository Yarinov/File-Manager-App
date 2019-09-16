//package com.yarinov.filemanagerapp.Document;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.yarinov.filemanagerapp.R;
//
//import java.io.File;
//import java.util.List;
//
//public class DocumentListActivity extends AppCompatActivity {
//
//    List<ApplicationInfo> installedApps;
//
//    ImageView docImage;
//    TextView docName, docDet;
//
//    ListView docListView;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_document_list);
//
//        docListView = findViewById(R.id.docList);
//
//        File
//
//        //Set the custom adapter to the list view
//        CustomAdapter appCustomAdapter = new CustomAdapter();
//
//        appListView.setAdapter(appCustomAdapter);
//    }
//
//
//    class CustomAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            //Return the size of arraylist -> The amount of installed apps
//            return installedApps.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//
//        @SuppressLint("ViewHolder")
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            //Set the custom layout as the current view
//            view = getLayoutInflater().inflate(R.layout.customapplist, null);
//
//            ApplicationInfo app = installedApps.get(i);
//
//            docImage = view.findViewById(R.id.appImageInList);
//            appName = view.findViewById(R.id.appNameInList);
//            appDet = view.findViewById(R.id.appDetInList);
//
//            //Set the icon and app name
//            appName.setText(String.valueOf(pm.getApplicationLabel(app)));
//            appImage.setImageDrawable(pm.getApplicationIcon(app));
//
//
//
//            return view;
//        }
//    }
//}
