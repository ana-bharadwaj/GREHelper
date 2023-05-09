package com.example.gre.Adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gre.Models.Phonetics;
import com.example.gre.R;
import com.example.gre.ViewHolders.PhoneticsViewHolder;

import java.io.IOException;
import java.util.List;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticsViewHolder> {
private Context context;

    public PhoneticsAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    private List<Phonetics> phoneticsList;
    @NonNull
    @Override
    public PhoneticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticsViewHolder(LayoutInflater.from(context).inflate(R.layout.phonetics_list_items,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticsViewHolder holder, int position) {
        holder.textView_phonetics.setText(phoneticsList.get(position).getText());
        holder.imageButton_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player= new MediaPlayer();
                try {
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource("https"+phoneticsList.get(position).getAudio());
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context,"Could not play audio",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }
}
