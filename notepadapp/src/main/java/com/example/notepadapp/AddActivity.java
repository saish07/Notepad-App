package com.example.notepadapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        save();
        back();

    }

    private void save() {

        findViewById(R.id.imgSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filename = ((EditText)findViewById(R.id.edtName)).getText().toString();

                if(filename.equals("")){

                    AlertDialog.Builder builder = new AlertDialog.Builder((AddActivity.this));
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setMessage("Enter filename");

                    builder.create().show();

                }else{

                    String flName = ((EditText)findViewById(R.id.edtName)).getText().toString();
                    String txtOfNotepad = ((EditText)findViewById(R.id.edtData)).getText().toString();

                    try {

                        FileOutputStream fos = openFileOutput(flName+".txt",MODE_APPEND);
                        fos.write(txtOfNotepad.getBytes());
                        fos.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    findViewById(R.id.edtName).setEnabled(false);
                    Toast.makeText(AddActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void back() {
        findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText fileName = (EditText)findViewById(R.id.edtName);
                String textNote = ((EditText)findViewById(R.id.edtData)).getText().toString();
                String textFilename = ((EditText)findViewById(R.id.edtName)).getText().toString();

                if(  ( (textNote.equals("") ) && (textFilename.equals("")) ) || (!fileName.isEnabled())){
                    Intent intent=new Intent(AddActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
