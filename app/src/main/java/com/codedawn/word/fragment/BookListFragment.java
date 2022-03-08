package com.codedawn.word.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedawn.word.R;
import com.codedawn.word.adapter.WordBookAdapter;
import com.codedawn.word.json.booklist.Book;
import com.codedawn.word.json.booklist.Cates;
import com.codedawn.word.util.GsonUtil;
import com.codedawn.word.util.LogUtil;
import com.codedawn.word.util.Okhttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class BookListFragment extends Fragment {

    private RecyclerView recyclerView;


    // 书单数据
    private List<Book> bookLists = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_book_list, container, false);
        recyclerView = root.findViewById(R.id.recycler_word_book_list);
        initRecyclerView();
        return root;
    }


    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new WordBookAdapter(bookLists));

    }


    public static BookListFragment newInstance(List<Book> bookList) {
        BookListFragment fragment = new BookListFragment();
        fragment.bookLists.addAll(bookList);

        return fragment;
    }

}