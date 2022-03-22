package com.codedawn.word.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codedawn.word.R;
import com.codedawn.word.json.wordbook.Trans;
import com.codedawn.word.json.wordbook.Word;
import com.codedawn.word.litepal.Vocabulary;
import com.codedawn.word.util.MediaUtil;

import java.util.List;

/**
 * 单词详情页
 */
public class WordDetailActivity extends BasicActivity {

    private TextView textWordName;

    private Vocabulary vocabulary;

    private TextView textWordPhoneUK;

    private TextView textWordPhoneUSA;

    private TextView textWordTrans;

    private ImageView imageWordStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        vocabulary = (Vocabulary) getIntent().getSerializableExtra("vocabulary");
        init();
        initData();
        initListener();
    }

    private void init() {
        textWordName = findViewById(R.id.text_word_name);

        textWordPhoneUK = findViewById(R.id.text_word_phone_uk);

        textWordPhoneUSA = findViewById(R.id.text_word_phone_usa);

        textWordTrans = findViewById(R.id.text_word_trans);

        imageWordStar = findViewById(R.id.image_word_star);
    }


    private void initListener() {
        Word word = vocabulary.getVocabularyJsonRootBean().getOutcontent().getWord();
        textWordPhoneUK.setOnClickListener(v -> {
            MediaUtil.play(word.getContent().getUkspeech());
        });
        textWordPhoneUSA.setOnClickListener(v -> {
            MediaUtil.play(word.getContent().getUsspeech());
        });

        imageWordStar.setOnClickListener(v -> {
            if (vocabulary.getFavorite() == 1) {
                Glide.with(this).load(R.drawable.icon_star).into(imageWordStar);
                vocabulary.setFavorite(0);
                vocabulary.save();
            }else {
                Glide.with(this).load(R.drawable.icon_star_fill).into(imageWordStar);
                vocabulary.setFavorite(1);
                vocabulary.save();
            }
        });
    }
    private void initData() {
        Word word = vocabulary.getVocabularyJsonRootBean().getOutcontent().getWord();
        textWordName.setText(word.getWordHead());

        textWordPhoneUK.setText(String.format("/%s/", word.getContent().getUkphone()));
        textWordPhoneUSA.setText(String.format("/%s/", word.getContent().getUsphone()));
        StringBuilder stringBuilder = new StringBuilder();
        List<Trans> transList = word.getContent().getTrans();

        for (Trans t : transList) {
            stringBuilder.append(t.getPos());
            stringBuilder.append(".");
            stringBuilder.append(t.getTranCn());
            stringBuilder.append("\n");
        }
        textWordTrans.setText(stringBuilder.toString());

        if (vocabulary.getFavorite() == 1) {
            Glide.with(this).load(R.drawable.icon_star_fill).into(imageWordStar);
        }
    }
}