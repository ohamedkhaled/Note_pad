package com.example.app424;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class    NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteAdapterVH> implements Filterable {

    private List<NoteModel> notesList;
    private List<NoteModel> getModelListFiltered;
    private Context context;
    private Selectnote selectnote;

    public NotesAdapter(List<NoteModel> notesList,Selectnote selectnote) {
        this.notesList = notesList;
        this.getModelListFiltered=notesList;
        this.selectnote=selectnote;
    }



    @Override
    public NotesAdapter.NoteAdapterVH onCreateViewHolder( ViewGroup parent, int viewType) {
        context= parent.getContext();
        return new NoteAdapterVH(LayoutInflater.from(context).inflate(R.layout.rows_recycel,null));
    }

    @Override
    public void onBindViewHolder( NotesAdapter.NoteAdapterVH holder, int position) {
        NoteModel notes =notesList.get(position);

        String title =notes.getTit();
        String pirfix=notes.getTit().substring(0,1);
        String time =notes.getTime();


        holder.data.setText(time);
        holder.titlenote.setText(title);
        holder.pirfix.setText(pirfix);

    }

    @Override
    public Filter getFilter() {
        Filter filter =new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults =new FilterResults();

                if(charSequence==null|charSequence.length()==0){
                    filterResults.count=getModelListFiltered.size();
                    filterResults.values=getModelListFiltered;
                }else {
                    String searchch =charSequence.toString().toLowerCase();
                    List<NoteModel> resultData=new ArrayList<>();

                    for(NoteModel noteModel:getModelListFiltered){

                        if(noteModel.getTit().toLowerCase().contains(searchch)){
                            resultData.add(noteModel);
                        }
                    }
                        filterResults.count=resultData.size();
                        filterResults.values=resultData;

                    }

              return filterResults;
                }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                notesList= (   List<NoteModel>) results.values;
                notifyDataSetChanged();

            }
        };

        return filter;
    }

    public interface Selectnote{

        void selectnote (NoteModel noteModel);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class NoteAdapterVH extends RecyclerView.ViewHolder {
     TextView titlenote,pirfix,data;
     ImageView iconnote;

        public NoteAdapterVH( View itemView) {
            super(itemView);
            titlenote=itemView.findViewById(R.id.titleNote);
            pirfix=itemView.findViewById(R.id.pirfixx);
            iconnote=itemView.findViewById(R.id.imagenote);
            data=itemView.findViewById(R.id.data);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectnote.selectnote(notesList.get(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position=getAdapterPosition();
                    Toast.makeText(itemView.getContext(),"Deleted:"+notesList.get(position).getTit(),Toast.LENGTH_LONG).show();
                    notesList.remove(position);
                    notifyItemRemoved(position);

                    return false;
                }
            });

        }
    }
}
