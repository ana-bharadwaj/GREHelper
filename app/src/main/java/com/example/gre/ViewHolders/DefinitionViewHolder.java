package com.example.gre.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gre.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_definitions,textView_example,textView_synonyms,textView_antonyms;
    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);
         textView_antonyms= itemView.findViewById(R.id.textView_antonyms);
         textView_definitions=itemView.findViewById(R.id.textView_definitions);
         textView_synonyms=itemView.findViewById(R.id.textView_synonyms);
         textView_example=itemView.findViewById(R.id.textView_examples);


    }
}
