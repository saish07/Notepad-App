package com.example.notepadapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Edit extends AppCompatActivity {

    public static final String KEY_NAME = "fileName";
    public static final String KEY_INFO = "noteInfo";
    public static final String KEY_POSITION = "clickedPosition";

    long size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        loadFileList();
        findViewById(R.id.imgListBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentList = new Intent(Edit.this, MainActivity.class);
                startActivity(intentList);
                finish();
            }
        });
    }

    private void loadFileList() {

        final List<FileItem> fileItems = new ArrayList<>();

        File file = getFilesDir();
        File[] files = file.listFiles();

        for (File fl : files) {
            Date date = new Date(fl.lastModified());

            size = fl.length();
            fileItems.add(new FileItem(R.drawable.ic_note_black_24dp, fl.getName(), "" + (new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(date)),SizeChange(size),""));
        }

        ((ListView) findViewById(R.id.lstListOfFiles)).setAdapter(new FileAdapter(this, fileItems));

        //opening individual file in new activity for editing with delete and back option
        ((ListView) findViewById(R.id.lstListOfFiles)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FileItem clickedItem = fileItems.get(position);

                String strFileName = clickedItem.txtFlName;

                //putting file's name and notepad text into bundles
                Bundle bundle = new Bundle();

                //bundle.putInt(KEY_POSITION,position);
                bundle.putString(KEY_NAME, strFileName);
                //bundle.putString(KEY_INFO,strNoteInfo);

                startActivity(new Intent(Edit.this, FileEdit.class).putExtras(bundle));
            }
        });

    }


    private String SizeChange(long size){

        String hrSize = null;

        double b = size;
        double k = size/1024.0;
        double m = ((size/1024.0)/1024.0);
        double g = (((size/1024.0)/1024.0)/1024.0);
        double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("");

        if ( t>1 ) {
            hrSize = dec.format(t).concat(" TB");
        } else if ( g>1 ) {
            hrSize = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            hrSize = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }
}

