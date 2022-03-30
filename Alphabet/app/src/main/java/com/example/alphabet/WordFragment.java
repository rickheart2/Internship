package com.example.alphabet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordFragment extends Fragment {

    private List<Letter> wordList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.word_fragment,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.word_recycler_view);
        wordList = LetterAdapter.wordList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        WordAdapter adapter = new WordAdapter(wordList);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
