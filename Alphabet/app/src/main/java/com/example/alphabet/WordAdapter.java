package com.example.alphabet;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {
    private List<Letter> mWordList;
    private Context mContext;
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
        mContext = parent.getContext();
        holder.wordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击事件
                int position = holder.getAdapterPosition();
                Letter word = mWordList.get(position);
                String str = word.getLetter();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,str);
                mContext.startActivity(intent);
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
