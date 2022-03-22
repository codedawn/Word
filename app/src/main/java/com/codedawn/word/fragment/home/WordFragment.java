package com.codedawn.word.fragment.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codedawn.word.R;
import com.codedawn.word.activity.BookDetailActivity;
import com.codedawn.word.activity.LearnWordActivity;
import com.codedawn.word.activity.SearchActivity;
import com.codedawn.word.activity.WordDetailActivity;
import com.codedawn.word.litepal.UserData;
import com.codedawn.word.util.LogUtil;
import com.google.android.material.button.MaterialButton;

/**
 * 首页
 */
public class WordFragment extends Fragment {

    private static final String TAG = WordFragment.class.getSimpleName();

    private ImageView imageCover;

    private CardView cardSearch;

    private MaterialButton buttonBegin;

    public WordFragment() {

    }

    public static WordFragment newInstance() {
        return new WordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("WordFragment","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtil.d(TAG,"onCreateView");

        View view = inflater.inflate(R.layout.fragment_word, container, false);
        imageCover = view.findViewById(R.id.image_cover);
        cardSearch = view.findViewById(R.id.card_search);
        buttonBegin = view.findViewById(R.id.button_begin);

        initData();
        initListener();
        return view;
    }

    private void initListener() {
        imageCover.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BookDetailActivity.class);
            startActivity(intent);
        });
        cardSearch.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            startActivity(intent);
        });

        buttonBegin.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LearnWordActivity.class);
//            intent.putExtra("vocabulary",)
            startActivity(intent);
        });

    }

    private void initData() {
        Glide.with(requireActivity()).load(UserData.getInstance().getBook().getCover()).into(imageCover);

    }
}