package com.codedawn.word.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;

import com.codedawn.word.R;
import com.codedawn.word.fragment.BookListFragment;
import com.codedawn.word.json.booklist.BookJsonRootBean;
import com.codedawn.word.util.DepthPageTransformer;
import com.codedawn.word.util.GsonUtil;
import com.codedawn.word.util.Okhttp;
import com.codedawn.word.util.ZoomOutPageTransformer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 书列表，展示可以学习的单词书
 */
public class BookListActivity extends BasicActivity {



    private ImageView imgRecover;

    private ImageView backImg;

    private ViewPager2 viewPager2;

    private TabLayout tabLayout;


    // 书单数据
    private BookJsonRootBean bookJsonRootBean ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        init();
        initListener();
        initData();


    }


    private void initData() {
        Okhttp.request("https://reciteword.youdao.com/reciteword/v1/books?le=en&sv=1.1", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //解析json
                           bookJsonRootBean=GsonUtil.parseJsonToBookJsonRootBean(Objects.requireNonNull(response.body()).string());
                            initViewPager();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }

    private void initViewPager() {
        // 关闭预加载
        viewPager2.setOffscreenPageLimit(ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT);  // 可以不设置 因为默认是 -1 默认不进行预加载
//        // 这个必须设置 不然仍然会启用预加载
//        ((RecyclerView)viewPager2.getChildAt(0)).getLayoutManager().setItemPrefetchEnabled(false);
//        // 设置缓存数量，对应 RecyclerView 中的 mCachedViews，即屏幕外的视图数量
//        ((RecyclerView)viewPager2.getChildAt(0)).setItemViewCacheSize(0);

        viewPager2.setPageTransformer(new ZoomOutPageTransformer());

//        viewPager2.setPageTransformer(new DepthPageTransformer());

        viewPager2.setAdapter(new FragmentStateAdapter(BookListActivity.this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return BookListFragment.newInstance(bookJsonRootBean.getCates().get(position).getBookList());
            }

            @Override
            public int getItemCount() {
                return bookJsonRootBean.getCates().size();
            }

        });

        // viewPager2 滑动监听
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });

        //smothscoll为true更平滑
        // 这里第四个参数一定要设置为false  如果设置为true时 我们在滑动时 BlankFragment的创建 和 销毁 都很正常
        // 一旦 我们通过 点击tabLayout时 如果两个tab距离过远  那么所有划过的tabLayout 都会创建和销毁BlankFragment 这显然不是我们想要的

        // tabLayout 和 viewPager 联动
        new TabLayoutMediator(tabLayout, viewPager2,true,true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(bookJsonRootBean.getCates().get(position).getCateName());
            }
        }).attach();
    }
    private void initListener() {
        backImg.setOnClickListener(v -> {
            finish();
        });
        imgRecover.setOnClickListener(v -> {
            initData();
        });
    }

    // 初始化控件
    private void init() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        imgRecover = findViewById(R.id.img_wb_recover);
        backImg = findViewById(R.id.backImg);
    }
}