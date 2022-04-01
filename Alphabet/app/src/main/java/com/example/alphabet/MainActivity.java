package com.example.alphabet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWords();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.data_container,new LetterFragment());

        transaction.commit();
    }

    private void initWords(){
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        String AWords = "admire:actor:agree";
        String BWords = "button:bag:boy";
        String CWords = "cat:copy:cold";
        String DWords = "dog:dead";
        String EWords = "easy:earth";
        String FWords = "future:force";
        String GWords = "general:glory";
        String HWords = "hyper:hacker";
        String IWords = "illegal:idea";
        String JWords = "junk:joy";
        String KWords = "kick:key";
        String LWords = "last:lazy";
        String MWords = "modern:mud";
        String NWords = "nest:normal";
        String OWords = "origin:orange";
        String PWords = "peek:pack";
        String QWords = "queue:query";
        String RWords = "root:roof";
        String SWords = "sort:south";
        String TWords = "technology:talk";
        String UWords = "uneasy:university";
        String VWords = "verse:version";
        String WWords = "witch:weight";
        String XWords = "xerox:xylophone";
        String YWords = "yacht:yam";
        String ZWords = "zeus:zero";
        editor.putString("A",AWords);
        editor.putString("B",BWords);
        editor.putString("C",CWords);
        editor.putString("D",DWords);
        editor.putString("E",EWords);
        editor.putString("F",FWords);
        editor.putString("G",GWords);
        editor.putString("H",HWords);
        editor.putString("I",IWords);
        editor.putString("J",JWords);
        editor.putString("K",KWords);
        editor.putString("L",LWords);
        editor.putString("M",MWords);
        editor.putString("N",NWords);
        editor.putString("O",OWords);
        editor.putString("P",PWords);
        editor.putString("Q",QWords);
        editor.putString("R",RWords);
        editor.putString("S",SWords);
        editor.putString("T",TWords);
        editor.putString("U",UWords);
        editor.putString("V",VWords);
        editor.putString("W",WWords);
        editor.putString("X",XWords);
        editor.putString("Y",YWords);
        editor.putString("Z",ZWords);
        editor.apply();
    }
}