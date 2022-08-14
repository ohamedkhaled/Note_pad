package com.example.app424;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recyclerViewFavFragment extends Fragment implements NotesAdapter.Selectnote{

    List<NoteModel> notesList;
    RecyclerView recyclerView;
    NotesAdapter notesAdapter;
    TextView textView;

    Context context ;

    public recyclerViewFavFragment(List<NoteModel> notesList) {
        this.notesList = notesList;

    }

    public List<NoteModel> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<NoteModel> notesList) {
        this.notesList = notesList;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.recyclerviewfragment, container, false);

        recyclerView = root.findViewById(R.id.recycelview);
        textView=root.findViewById(R.id.text);



      /*  Bundle data =getArguments();
        if(data !=null) {
            notesList.add(new NoteModel(data.getString("ti"), data.getString("dis")));
        }
*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));

        notesAdapter= new NotesAdapter(notesList,this);
        recyclerView.setAdapter(notesAdapter);

        return root;
    }

    @Override
    public void selectnote(NoteModel noteModel) {

        startActivity(new Intent(this.getContext(), selectNotes.class).putExtra("data",noteModel));


    }
}
