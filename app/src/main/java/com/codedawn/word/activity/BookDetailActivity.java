package com.codedawn.word.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;

import com.codedawn.word.R;
import com.codedawn.word.fragment.WordListFragment;
import com.codedawn.word.litepal.Vocabulary;
import com.codedawn.word.util.GsonUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.litepal.LitePal;

import java.util.List;


/**
 * 点击书，然后进入书详情页，当前页面是详情页
 */
public class BookDetailActivity extends BasicActivity {


    private TabLayout tabLayout;

    private ViewPager2 viewPager2;

    private List<Vocabulary> vocabularyList;

    private String[] tabs = {"已学单词", "未学单词", "掌握单词", "收藏单词"};


    private ImageView imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        init();
        initData();
        initViewPage();
        initListener();
    }


    private void initViewPage() {
        viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);

        viewPager2.setAdapter(new FragmentStateAdapter(BookDetailActivity.this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return WordListFragment.newInstance(vocabularyList);
            }

            @Override
            public int getItemCount() {
                return tabs.length;
            }
        });

        new TabLayoutMediator(tabLayout, viewPager2, true, false, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        }).attach();
    }


    private void initData() {
//        Book book = UserData.getInstance().getBook();
//        if (book != null) {
//
//        }

         vocabularyList = LitePal.findAll(Vocabulary.class);
        for (Vocabulary v : vocabularyList) {
            v.setVocabularyJsonRootBean(GsonUtil.parseJsonToVocabularyJsonRootBean(v.getWordJson()));
        }
    }
    private void init() {
        tabLayout = findViewById(R.id.tabLayout_word_list);
        viewPager2 = findViewById(R.id.viewPager2_word_list);
        imageBack = findViewById(R.id.image_back);
    }

    private void initListener() {
        imageBack.setOnClickListener(v -> {
            finish();
        });
    }

}