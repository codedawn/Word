package com.codedawn.word.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codedawn.word.R;
import com.codedawn.word.adapter.WordBookListAdapter;
import com.codedawn.word.json.booklist.Book;

import java.util.List;

/**
 * 单词书列表
 */
public class BookListFragment extends Fragment {

    private RecyclerView recyclerView;


    // 书单数据
    private  List<Book> bookLists;


    public BookListFragment() {

    }

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
        recyclerView.setAdapter(new WordBookListAdapter(bookLists));

    }


    public static BookListFragment newInstance(List<Book> bookList) {
        BookListFragment bookListFragment = new BookListFragment();
        bookListFragment.bookLists = bookList;
        return bookListFragment;
    }

}