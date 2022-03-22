package com.codedawn.word.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codedawn.word.R;
import com.codedawn.word.adapter.WordListAdapter;
import com.codedawn.word.litepal.Vocabulary;
import com.google.android.material.button.MaterialButton;

import java.util.List;


public class WordListFragment extends Fragment {


    private List<Vocabulary> vocabularyList;

    private TextView textWordNum;

    private MaterialButton buttonEdit;

    private RecyclerView recyclerView;


    public WordListFragment() {

    }


    public static WordListFragment newInstance(List<Vocabulary> vocabularyList) {
        WordListFragment wordListFragment = new WordListFragment();
        wordListFragment.vocabularyList = vocabularyList;
        return wordListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);
        textWordNum = view.findViewById(R.id.text_word_num);
        buttonEdit = view.findViewById(R.id.button_edit);
        recyclerView = view.findViewById(R.id.recycler_word_list);
        init();
        initRecycler();
        return view;
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new WordListAdapter(vocabularyList));
    }

    private void init() {
        textWordNum.setText("单词数："+vocabularyList.size());
    }


}