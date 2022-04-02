package com.example.alphabet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LetterFragment extends Fragment {
    private int layout_flag = 1;
    private List<Letter> letterList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.letter_fragment,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.letter_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),2);
        recyclerView.setLayoutManager(linearLayoutManager);
        LetterAdapter adapter = new LetterAdapter(letterList);
        recyclerView.setAdapter(adapter);

        initLetters();

        MainActivity mainActivity = (MainActivity) this.getActivity();
        ImageButton button_swap = (ImageButton) mainActivity.findViewById(R.id.swap_layout);
        button_swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (layout_flag) {
                    case 1:
                        recyclerView.setLayoutManager(gridLayoutManager);
                        layout_flag = 2;
                        break;
                    case 2:
                        recyclerView.setLayoutManager(linearLayoutManager);
                        layout_flag = 1;
                }
            }
        });
        return view;
    }

    private void initLetters(){
        letterList.clear();
        Character temp = 'A';
        for (int i = 0; i < 26; i++) {
            String temp1 = temp.toString();
            Letter letter = new Letter(temp1);
            letterList.add(letter);
            temp++;
        }
    }
}
