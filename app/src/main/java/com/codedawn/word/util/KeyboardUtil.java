package com.codedawn.word.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.codedawn.word.R;
import com.codedawn.word.activity.KeyboardSpellActivity;
import com.codedawn.word.view.CustomKeyboardView;

public class KeyboardUtil {

    private static final String TAG = KeyboardUtil.class.getSimpleName();
    private Context mContext;
    private KeyboardView mKeyboardView;
    private EditText mEditText;
    private TextView textWord;
    private KeyboardSpellActivity keyboardSpellActivity;
    private int wordIndex;

    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(@NonNull Message msg) {

        }
    };



    @SuppressLint("ClickableViewAccessibility")
    public KeyboardUtil(Context context, KeyboardView keyboardView, EditText editText, TextView textWord, KeyboardSpellActivity keyboardSpellActivity) {
        this.mContext = context;
        this.mKeyboardView = keyboardView;
        this.mEditText = editText;
        this.textWord = textWord;
        this.keyboardSpellActivity = keyboardSpellActivity;
        if (keyboardSpellActivity.getWordHead().length()==0) {
            LogUtil.d(TAG,"拼写单词长度是0");
            keyboardSpellActivity.finish();
        }
        initKeyboard();
        mEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    hideSystemKeyboard((EditText) v);
                    mKeyboardView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v instanceof EditText) {
                    if (!hasFocus) {
                        mKeyboardView.setVisibility(View.GONE);
                    } else {
                        hideSystemKeyboard((EditText) v);
                        mKeyboardView.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                System.out.println();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                char c = s.charAt(start);
                if (c == keyboardSpellActivity.getWordHead().charAt(wordIndex)) {
                    textWord.setText(String.format("%s%s", textWord.getText(),c));
                    wordIndex++;
                    rightSpell();
                }else {
                    MediaUtil.playLocalFile(ConstantData.WRONG_SIGN);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println();
            }
        });
    }

    private void rightSpell() {
        if (wordIndex >= keyboardSpellActivity.getWordHead().length()) {
            LogUtil.d(TAG,"拼写完成");
            MediaUtil.playLocalFile(ConstantData.RIGHT_SIGN);
            handler.postDelayed(() -> keyboardSpellActivity.finish(), 500);
        }
    }

    private void hideSystemKeyboard(EditText v) {
        this.mEditText = v;
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm == null){
            return;
        }
        boolean isOpen = imm.isActive();
        if (isOpen) {
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            v.setShowSoftInputOnFocus(false);
        }else{
            v.setInputType(0);
        }
    }

    public char getHintLetter() {
        return keyboardSpellActivity.getWordHead().charAt(wordIndex);
    }

    private void initKeyboard() {
        Keyboard keyboard = new Keyboard(mContext, R.xml.key_board);
        mKeyboardView.setKeyboard(keyboard);
        mKeyboardView.setEnabled(true);

//        mKeyboardView.setVisibility(View.GONE);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(listener);
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
//            if(primaryCode == -4 || primaryCode == -5){
//                mKeyboardView.setPreviewEnabled(false);
//            }else{
//                mKeyboardView.setPreviewEnabled(true);
//            }
        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {

            Editable editable = mEditText.getText();
            int start = mEditText.getSelectionStart();
            int end = mEditText.getSelectionEnd();
            if(primaryCode == Keyboard.KEYCODE_DONE){
                mKeyboardView.setVisibility(View.GONE);
            }else if(primaryCode == Keyboard.KEYCODE_DELETE){
                if(editable != null && editable.length() > 0){
                    if(start == end){
                        editable.delete(start -1, start);
                    }else{
                        editable.delete(start,end);
                    }
                }
            }else{
                editable.replace(start,end,Character.toString((char) primaryCode));
            }
        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    public boolean isHint() {
        return wordIndex<keyboardSpellActivity.getWordHead().length();
    }
}