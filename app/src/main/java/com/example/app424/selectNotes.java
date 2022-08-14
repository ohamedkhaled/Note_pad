package com.example.app424;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class selectNotes extends AppCompatActivity {

    TextView tvtitle,tvdescr,tvurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_notes);


        tvtitle=findViewById(R.id.titleNoteIN);
        tvdescr=findViewById(R.id.discNoteIN);
        tvurl=findViewById(R.id.urllIN);


        Intent intent = getIntent();

        if(intent.getExtras()!=null){

            NoteModel noteModel =(NoteModel) intent.getSerializableExtra("data");

            tvtitle.setText(noteModel.getTit());
            tvdescr.setText(noteModel.getDis());
            tvurl.setText(noteModel.getURL());
        }

    }
}