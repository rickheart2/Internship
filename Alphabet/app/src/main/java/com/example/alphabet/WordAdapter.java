package com.example.alphabet;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        MainActivity mainActivity = (MainActivity) mContext;
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
        holder.wordButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(mContext)
                        .setTitle("Tips")
                        .setMessage("Are you sure you want to delete the word?")
                        //设置对话框的按钮
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int position = holder.getAdapterPosition();
                                mWordList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position,getItemCount());
                                StringBuilder builder = new StringBuilder();
                                for (Letter l:mWordList) {
                                    builder.append(l.getLetter()).append(":");
                                }
                                int len = builder.length();
                                if(len!=0){
                                    builder.deleteCharAt(builder.length()-1);
                                }
                                String str = builder.toString();
                                SharedPreferences.Editor editor = mContext.getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                                editor.putString(LetterAdapter.contentLetter,str);
                                editor.apply();
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
                return true;
            }
        });

        ImageButton button_add = (ImageButton) mainActivity.findViewById(R.id.add_button);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = mainActivity.getLayoutInflater().inflate(R.layout.dialog, null);
                final EditText editText = (EditText) v.findViewById(R.id.dialog_edit);
                AlertDialog dialog = new AlertDialog.Builder(mainActivity)
                        .setTitle("Add new word")
                        .setView(v)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String content = editText.getText().toString();
                                mWordList.add(new Letter(content));
                                notifyItemRangeChanged(getItemCount(),getItemCount());
                                SharedPreferences pref = mainActivity.getSharedPreferences("data",Context.MODE_PRIVATE);
                                String dataString = pref.getString(LetterAdapter.contentLetter,"");
                                SharedPreferences.Editor editor = mainActivity.getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                                editor.putString(LetterAdapter.contentLetter,dataString+":"+content);
                                editor.apply();
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
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
