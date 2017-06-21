package com.example.notepadapp;

/**
 * Created by saish on 19/6/17.
 */

public class FileItem {

    public int imgId;
    public String txtFlName;
    public String txtFlMdfd;
    public String txtFsz;
    public String txtNotepad;

    public FileItem(int imgId, String txtFlName, String txtFlMdfd, String txtFsz,String txtNotepad) {
        this.imgId = imgId;
        this.txtFlName = txtFlName;
        this.txtFlMdfd = txtFlMdfd;
        this.txtFsz = txtFsz;
        this.txtNotepad = txtNotepad;
    }
}



