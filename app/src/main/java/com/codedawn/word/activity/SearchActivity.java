package com.codedawn.word.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codedawn.word.R;
import com.codedawn.word.adapter.SearchAdapter;
import com.codedawn.word.litepal.Vocabulary;
import com.codedawn.word.util.GsonUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BasicActivity {

    private TextView textCancel;

    private EditText editSearch;

    private RecyclerView recyclerSearch;
    private SearchAdapter searchAdapter;

    private RelativeLayout layoutNothing;

    private final List<Vocabulary> vocabularyList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        initListener();

        showSoftInputFromWindow(this,editSearch);
    }

    private void init() {
        textCancel = findViewById(R.id.text_cancel);
        editSearch = findViewById(R.id.edit_search);
        recyclerSearch = findViewById(R.id.recycler_search);
        layoutNothing = findViewById(R.id.layout_search_nothing);


        recyclerSearch.setLayoutManager(new LinearLayoutManager(this));
        searchAdapter = new SearchAdapter(vocabularyList);
        recyclerSearch.setAdapter( searchAdapter);
    }



    private void initListener() {
        textCancel.setOnClickListener(v -> {
            finish();
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().trim().replaceAll("\r\n|\r|\n", "");
                if ("".equals(str)) {
                    recyclerSearch.setVisibility(View.GONE);
                    layoutNothing.setVisibility(View.VISIBLE);
                }else {
                    queryVocabulary(str);
                    recyclerSearch.setVisibility(View.VISIBLE);
                    layoutNothing.setVisibility(View.GONE);
                }
            }
        });
    }


    private void queryVocabulary(String pref) {
        List<Vocabulary> vList = LitePal.where("wordhead like ?" , pref + "%").find(Vocabulary.class);

        for (Vocabulary v : vList) {
            v.setVocabularyJsonRootBean(GsonUtil.parseJsonToVocabularyJsonRootBean(v.getWordJson()));
        }
        this.vocabularyList.clear();
        this.vocabularyList.addAll(vList);
        searchAdapter.notifyDataSetChanged();
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        //显示软键盘
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //如果上面的代码没有弹出软键盘 可以使用下面另一种方式
        //InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // imm.showSoftInput(editText, 0);
    }

}