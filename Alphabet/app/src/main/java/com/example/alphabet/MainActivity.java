package com.example.alphabet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Letter> letterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLetters();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        LetterAdapter adapter = new LetterAdapter(letterList);
        recyclerView.setAdapter(adapter);
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
}