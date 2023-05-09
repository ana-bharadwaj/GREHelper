package com.example.gre.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gre.Models.Meanings;
import com.example.gre.R;
import com.example.gre.ViewHolders.MeaningViewHolder;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningViewHolder> {
    private Context context;
    private List<Meanings> meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
holder.textView_partOfSpeech.setText("Parts of speech"+meaningsList.get(position).getPartOfSpeech());
    holder.recyclerView_definitions.setHasFixedSize(true);
    holder.recyclerView_definitions.setLayoutManager(new GridLayoutManager(context,1));
DefinitionAdapter definitionAdapter= new DefinitionAdapter(context,meaningsList.get(position).getDefinations());
holder.recyclerView_definitions.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
