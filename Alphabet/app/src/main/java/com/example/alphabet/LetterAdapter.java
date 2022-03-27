package com.example.alphabet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.ViewHolder> {
    private List<Letter> mLetterList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        Button letterButton;

        public ViewHolder(View view){
            super(view);
            letterButton = (Button) view.findViewById(R.id.letter_button);
        }
    }

    public LetterAdapter(List<Letter> letterList){
        mLetterList = letterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Letter letter = mLetterList.get(position);
        holder.letterButton.setText(letter.getLetter());
    }

    @Override
    public int getItemCount() {
        return mLetterList.size();
    }
}
