package com.yarinov.filemanagerapp.InstallPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yarinov.filemanagerapp.R;

import java.util.ArrayList;
import java.util.List;

public class InstallPackageActivity extends AppCompatActivity {

    List<ApplicationInfo> installedApps;
    PackageManager pm;

    ImageView appImage;
    TextView appName, appDet;

    ListView appListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_package);

        appListView = findViewById(R.id.appList);

        //Get all the installed apps in phone and sort them by User-Installed and System-Installed
        installedApps = getInstalledApps();

        //Set the custom adapter to the list view
        CustomAdapter appCustomAdapter = new CustomAdapter();

        appListView.setAdapter(appCustomAdapter);

    }

    public List<ApplicationInfo> getInstalledApps(){

        List<ApplicationInfo> installedApps = new ArrayList<>();

        int flags = PackageManager.GET_META_DATA |
                PackageManager.GET_SHARED_LIBRARY_FILES |
                PackageManager.GET_UNINSTALLED_PACKAGES;

        pm = getPackageManager();
        List<ApplicationInfo> applications = pm.getInstalledApplications(flags);
        for (ApplicationInfo appInfo : applications) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                // System application
            } else {
                // Installed by user
                installedApps.add(appInfo);
            }
        }

        return installedApps;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            //Return the size of arraylist -> The amount of installed apps
            return installedApps.size();
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
            view = getLayoutInflater().inflate(R.layout.customapplist, null);

            ApplicationInfo app = installedApps.get(i);

            appImage = view.findViewById(R.id.appImageInList);
            appName = view.findViewById(R.id.appNameInList);
            appDet = view.findViewById(R.id.appDetInList);

            //Set the icon and app name
            appName.setText(String.valueOf(pm.getApplicationLabel(app)));
            appImage.setImageDrawable(pm.getApplicationIcon(app));



            return view;
        }
    }
}
