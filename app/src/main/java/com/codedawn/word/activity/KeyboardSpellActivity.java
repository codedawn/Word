package com.codedawn.word.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.codedawn.word.R;

import com.codedawn.word.litepal.Vocabulary;
import com.codedawn.word.util.KeyboardUtil;
import com.codedawn.word.view.CustomKeyboardView;

import java.io.Serializable;

public class KeyboardSpellActivity extends BasicActivity {

    private static final String TAG = KeyboardSpellActivity.class.getSimpleName();

    private CustomKeyboardView keyboardView;

    private TextView textWord;

    private EditText editText;

    private Vocabulary vocabulary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_keyboard);
        Intent intent = getIntent();
        vocabulary = (Vocabulary) intent.getSerializableExtra("vocabulary");
//        assert vocabulary != null:"vocabulary is null";
        init();
        KeyboardUtil keyboardUtil = new KeyboardUtil(this, keyboardView, editText,textWord,this);
        keyboardView.setKeyboardUtil(keyboardUtil);
    }

    private void init() {
        keyboardView = findViewById(R.id.keyboard);
        textWord = findViewById(R.id.text_keyboard_word);
        editText = findViewById(R.id.edit_text);
    }

    public String getWordHead() {
        return vocabulary.getWordHead();
    }



}
