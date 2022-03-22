package com.codedawn.word.activity;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.util.AttributeSet;

import com.codedawn.word.R;

public class WordKeyboardView extends KeyboardView {


    public WordKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Keyboard keyboard = new Keyboard(context, R.xml.key_board);
        this.setKeyboard(keyboard);


    }
}
