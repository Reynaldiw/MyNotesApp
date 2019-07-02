package com.reynaldiwijaya.mynotesapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reynaldiwijaya.mynotesapp.CostumOnItemClickListener;
import com.reynaldiwijaya.mynotesapp.NoteAddUpdateActivity;
import com.reynaldiwijaya.mynotesapp.R;
import com.reynaldiwijaya.mynotesapp.entity.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<Note> listNote = new ArrayList<>();
    private Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Note> getListNote() {
        return listNote;
    }

    public void setListNote(ArrayList<Note> listNote) {

        if (listNote.size() > 0) {
            this.listNote.clear();
        }
        this.listNote.addAll(listNote);

        notifyDataSetChanged();
    }

    public void addItem(Note note) {
        this.listNote.add(note);
        notifyItemInserted(listNote.size() - 1);
    }

    public void updateItem(int position, Note note) {
        this.listNote.set(position, note);
        notifyItemChanged(position, note);
    }

    public void removeItem(int position) {
        this.listNote.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listNote.size());
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_note,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder viewHolder, int i) {
        final Note note = listNote.get(i);
        viewHolder.tvTitle.setText(note.getTitle());
        viewHolder.tvDescription.setText(note.getDescription());
        viewHolder.tvDate.setText(note.getDate());
        viewHolder.cvNote.setOnClickListener(new CostumOnItemClickListener(i, new CostumOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, NoteAddUpdateActivity.class);
                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position);
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note);
                activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvDescription, tvDate;
        final CardView cvNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
