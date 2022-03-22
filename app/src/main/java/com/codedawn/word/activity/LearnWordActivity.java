package com.codedawn.word.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.codedawn.word.R;
import com.codedawn.word.json.wordbook.RealSentences;
import com.codedawn.word.json.wordbook.Sentences;
import com.codedawn.word.json.wordbook.Trans;
import com.codedawn.word.json.wordbook.Word;
import com.codedawn.word.litepal.Vocabulary;
import com.codedawn.word.util.GsonUtil;
import com.codedawn.word.util.LogUtil;
import com.codedawn.word.util.MediaUtil;

import org.litepal.LitePal;

import java.util.List;

public class LearnWordActivity extends BasicActivity {

    private static final String TAG = LearnWordActivity.class.getSimpleName();

    private List<Vocabulary> vocabularyList;

    private TextView textWord;

    private TextView textPhone;

    private TextView textOtherTrans;

    private LinearLayout linearExample;

    private LinearLayout linearTrans;

    private RelativeLayout relativeKnow;

    private TextView textWordTrans;

    private TextView textExample;


    private CardView cardWordTrans;


    private TextView textLearnPhoneUk;

    private TextView textLearnPhoneUsa;

    private TextView textHint;

    private LinearLayout linearLearnControl;

    private RelativeLayout relativeNextWord;

    private ImageView imageLearnBack;

    private RelativeLayout relativeUnclear;

    private ImageView imageLearnStar;

    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_word);
        init();
        initData();
        initListener();

        nextVocabulary();
    }


    private void showVocabulary() {
        Vocabulary vocabulary = vocabularyList.get(index);
        Word word = vocabulary.getVocabularyJsonRootBean().getOutcontent().getWord();
        textWord.setText(word.getWordHead());

        textLearnPhoneUk.setText(String.format("/%s/", word.getContent().getUkphone()));
        textLearnPhoneUsa.setText(String.format("/%s/", word.getContent().getUsphone()));

        if (vocabulary.getFavorite() == 1) {
            Glide.with(this).load(R.drawable.icon_star_fill).into(imageLearnStar);
        }

        StringBuilder sb = new StringBuilder();
        List<Sentences> realSentencesList = word.getContent().getSentence().getSentences();
        for (Sentences s : realSentencesList) {
            sb.append(s.getSContent());
            sb.append("\n");
            sb.append(s.getSCn());
            break;
//            sb.append("\n");
        }
//        sb.delete(sb.length() - 2, sb.length());
        textExample.setText(sb.toString());

        //单词中文翻译
        sb.delete(0, sb.length());
        List<Trans> transList = word.getContent().getTrans();
        for (Trans t : transList) {
            sb.append(t.getPos());
            sb.append(".");
            sb.append(t.getTranCn());
            sb.append("\n");
        }
        textWordTrans.setText(sb.toString());

        //其他翻译，中英结合
        sb.delete(0, sb.length());
        Trans t;
        for (int i = 0; i < transList.size(); i++) {
            t = transList.get(i);
            sb.append(t.getPos()).append(". ").append(t.getTranOther());
            if (i < transList.size() - 1) {
                sb.append("\n");
            }
        }
        textOtherTrans.setText(sb.toString());
    }

    private void initData() {
        vocabularyList = LitePal.where("learned = 0 and nexttime < ?", String.valueOf(System.currentTimeMillis() / 1000)).limit(40).find(Vocabulary.class);
        for (Vocabulary v : vocabularyList) {
            v.setVocabularyJsonRootBean(GsonUtil.parseJsonToVocabularyJsonRootBean(v.getWordJson()));
        }


    }

    private void initListener() {

        //点击认识
        relativeKnow.setOnClickListener(v -> {

            showView();
        });
        relativeUnclear.setOnClickListener(v -> {
            Intent intent = new Intent(this, KeyboardSpellActivity.class);
            intent.putExtra("vocabulary", vocabularyList.get(index));
            startActivity(intent);
        });
        relativeNextWord.setOnClickListener(v -> {
            if (index > vocabularyList.size() - 1) {
                LogUtil.d(TAG, "单词学习完毕");
                return;
            }
            vocabularyKnow();
            nextVocabulary();
        });


        textLearnPhoneUk.setOnClickListener(v -> {
            Vocabulary vocabulary = vocabularyList.get(index);
            Word word = vocabulary.getVocabularyJsonRootBean().getOutcontent().getWord();
            MediaUtil.play(word.getContent().getUkspeech());
        });
        textLearnPhoneUsa.setOnClickListener(v -> {
            Vocabulary vocabulary = vocabularyList.get(index);
            Word word = vocabulary.getVocabularyJsonRootBean().getOutcontent().getWord();
            MediaUtil.play(word.getContent().getUsspeech());
        });

        imageLearnBack.setOnClickListener(v -> {
            finish();
        });
        imageLearnStar.setOnClickListener(v -> {
            Vocabulary vocabulary = vocabularyList.get(index);
            if (vocabulary.getFavorite() == 1) {
                Glide.with(this).load(R.drawable.icon_star).into(imageLearnStar);
                vocabulary.setFavorite(0);
                vocabulary.save();
            }else {
                Glide.with(this).load(R.drawable.icon_star_fill).into(imageLearnStar);
                vocabulary.setFavorite(1);
                vocabulary.save();
            }
        });
    }

    //下一个单词
    private void nextVocabulary() {
        index++;
        showVocabulary();
        hideView();
    }

    private void vocabularyKnow() {
        Vocabulary vocabulary = vocabularyList.get(index);
        vocabulary.setLevel(vocabulary.getLevel() + 1);
        if (vocabulary.getLevel() > 7) {
            vocabulary.setLearned(1);
        } else {
            vocabulary.setNextTime(getNextTime(vocabulary.getLevel()));
        }
        vocabulary.save();
    }


    /**
     * 获取下一次记忆周期的时间
     * @param level
     * @return
     */
    private long getNextTime(int level) {
        long n = System.currentTimeMillis() / 1000;
        switch (level) {
            case 1:
                return n + 5 * 60;
            case 2:
                return n + 30 * 60;
            case 3:
                return n + 12 * 60 * 60;
            case 4:
                return n + 24 * 60 * 60;
            case 5:
                return n + 2 * 24 * 60 * 60;
            case 6:
                return n + 4 * 24 * 60 * 60;
            case 7:
                return n + 7 * 24 * 60 * 60;
            default:
                return 0;
        }
    }

    private void init() {
        textWord = findViewById(R.id.text_word);
        textOtherTrans = findViewById(R.id.text_other_trans);
        linearExample = findViewById(R.id.linear_example);
        linearTrans = findViewById(R.id.linear_trans);
        relativeKnow = findViewById(R.id.relative_know);
        textWordTrans = findViewById(R.id.text_word_trans);
        textExample = findViewById(R.id.text_example);
        cardWordTrans = findViewById(R.id.card_word_trans);
        textLearnPhoneUk = findViewById(R.id.text_learn_phone_uk);
        textLearnPhoneUsa = findViewById(R.id.text_learn_phone_usa);
        textHint = findViewById(R.id.text_hint);
        linearLearnControl = findViewById(R.id.linear_learn_control);
        relativeNextWord = findViewById(R.id.relative_next_word);
        imageLearnBack = findViewById(R.id.image_learn_back);
        relativeUnclear = findViewById(R.id.relative_unclear);
        imageLearnStar = findViewById(R.id.image_learn_star);

        hideView();
    }

    private void showView() {
        linearTrans.setVisibility(View.VISIBLE);
        linearExample.setVisibility(View.VISIBLE);
        cardWordTrans.setVisibility(View.VISIBLE);
        relativeNextWord.setVisibility(View.VISIBLE);
        textHint.setVisibility(View.GONE);
        linearLearnControl.setVisibility(View.GONE);
    }
    private void hideView() {
        cardWordTrans.setVisibility(View.GONE);
        linearTrans.setVisibility(View.GONE);
        linearExample.setVisibility(View.GONE);
        relativeNextWord.setVisibility(View.GONE);
        textHint.setVisibility(View.VISIBLE);
        linearLearnControl.setVisibility(View.VISIBLE);
    }
}