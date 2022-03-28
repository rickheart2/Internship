package com.example.alphabet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private List<Letter> mWordList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        Button wordButton;
        public ViewHolder(View view){
            super(view);
            wordButton = (Button) view.findViewById(R.id.letter_button);
        }
    }

    public WordAdapter(List<Letter> WordList){
        mWordList = WordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击事件
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Letter letter = mWordList.get(position);
        holder.wordButton.setText(letter.getLetter());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
