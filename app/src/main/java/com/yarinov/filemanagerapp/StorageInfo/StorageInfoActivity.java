package com.yarinov.filemanagerapp.StorageInfo;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.hedan.piechart_library.PieChartBean;
import com.hedan.piechart_library.PieChart_View;
import com.yarinov.filemanagerapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class StorageInfoActivity extends AppCompatActivity {

    String [] sections;
    ListView sectionListView;

    String [] sectionsColors;
    int [] sectionValue;

    TextView sectionName, sectionSize;
    ProgressBar sectionProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_info);

        sectionListView = findViewById(R.id.sectionListView);



        sections = new String[]{"Images", "Documents", "Videos", "Installed Apps", "Archive Files", "Audio", "Others", "Free Space"};
        sectionsColors = new String[] {"#96D65E", "#5B995A", "#F8F17F", "#F3A800", "#B58F39" , "#824026", "#431F07", "#eeeeee"};
        sectionValue = new int[] {1, 1, 1, 1, 1, 1, 1, 10};

        //Set the custom adapter to the list view
        CustomAdapter sectionCustomAdapter = new CustomAdapter();

        sectionListView.setAdapter(sectionCustomAdapter);

        PieChart_View pieView = (PieChart_View) findViewById(R.id.pie_view);
        ArrayList<PieChartBean> lists = new ArrayList<>();

        for (int i = 0; i < sections.length; i++) {
            lists.add(new PieChartBean(Color.parseColor(sectionsColors[i]), sectionValue[i]));
        }

        pieView.setData(lists);

    }


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            //Return the size of arraylist -> The amount of installed apps
            return sections.length-1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @SuppressLint("ViewHolder")
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //Set the custom layout as the current view
            view = getLayoutInflater().inflate(R.layout.custom_section_list, null);

            sectionName = view.findViewById(R.id.sectionName);
            sectionSize = view.findViewById(R.id.sectionSize);
           // sectionProgressBar = view.findViewById(R.id.sectionProBar);

            //Set the icon and app name
            sectionName.setText(String.valueOf(sections[i]));

            NumberProgressBar sectionProgressBar = view.findViewById(R.id.sectionProgressBar);

            sectionProgressBar.setProgress(30);
            sectionProgressBar.setReachedBarColor(Color.parseColor(sectionsColors[i]));
            sectionProgressBar.setProgressTextColor(Color.parseColor("#000000"));

            return view;
        }

    }

}
