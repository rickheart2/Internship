package com.example.alphabet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Letter> letterList = new ArrayList<>();

    private int layout_flag = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLetters();
        initWords();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.letter_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(linearLayoutManager);
        LetterAdapter adapter = new LetterAdapter(letterList);
        recyclerView.setAdapter(adapter);

        ImageButton button_swap = (ImageButton) findViewById(R.id.swap_layout);
        button_swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (layout_flag) {
                    case 1:
                        recyclerView.setLayoutManager(linearLayoutManager);
                        layout_flag = 2;
                        break;
                    case 2:
                        recyclerView.setLayoutManager(gridLayoutManager);
                        layout_flag = 1;
                }
            }
        });
    }

    private void initLetters(){
        Character temp = 'A';
        for (int i = 0; i < 26; i++) {
            String temp1 = temp.toString();
            Letter letter = new Letter(temp1);
            letterList.add(letter);
            temp++;
        }
    }
    private void initWords(){
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        String AWords = "admire:actor:agree";
        String BWords = "button:bag:boy";
        String CWords = "cat:copy:cold";
        String DWords = "dog:dead";
        String EWords = "easy:earth";
        editor.putString("A",AWords);
        editor.putString("B",BWords);
        editor.putString("C",CWords);
        editor.putString("D",DWords);
        editor.putString("E",EWords);
        editor.apply();
    }
}