package com.example.catatanharian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private ViewHolder.ClikckHandler handler;
    private final ArrayList<Catatan> data = new ArrayList<>();
    public MyListAdapter(ViewHolder.ClikckHandler handler) {
        this.handler = handler;
    }


    public void setData(ArrayList<Catatan> newData){
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_catatan, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Catatan catatan = data.get(position);
        holder.namaFileTextView.setText(catatan.getNamaFile());
        holder.isiCatatanTextView.setText(catatan.getIsiCatatan());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        final TextView namaFileTextView;
        final TextView isiCatatanTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaFileTextView = itemView.findViewById(R.id.et1);
            isiCatatanTextView = itemView.findViewById(R.id.et2);

            //buat hapus file
            itemView.setOnLongClickListener(view -> {
                handler.onLongClick(data.get(getAdapterPosition()).getNamaFile());
                return true;
            });
        }


         interface ClikckHandler{
            void onLongClick(String namaFile);
        }

    }
}
