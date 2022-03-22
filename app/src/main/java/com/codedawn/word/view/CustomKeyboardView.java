package com.codedawn.word.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;

import android.util.AttributeSet;

import androidx.annotation.DrawableRes;

import com.codedawn.word.R;
import com.codedawn.word.util.KeyboardUtil;

import java.lang.reflect.Field;
import java.util.List;

public class CustomKeyboardView extends KeyboardView {

    private Context context;
    private Paint paint;
    private Rect bounds;

    private KeyboardUtil keyboardUtil;

    public CustomKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        bounds = new Rect();
        this.context = context;

    }

    public CustomKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        bounds = new Rect();
        this.context = context;
    }

    /**
     * 重写这个方法是为了可以绘制一些特殊按键
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas) {
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            if(keyboardUtil.isHint()&&key.codes[0] ==(int) keyboardUtil.getHintLetter()){
                //绘制普通信息文本的背景
                drawBackground(R.drawable.keyboard_hint_bg,canvas,key);
                //绘制普通信息的文本信息
                drawText(canvas,key);

            }else{
                //绘制特殊按键
                //绘制普通信息文本的背景
                drawBackground(R.drawable.keyboard_bg,canvas,key);
                //绘制普通信息的文本信息
                drawText(canvas,key);
            }

        }

    }



    private void drawBackground(@DrawableRes int drawableId, Canvas canvas, Keyboard.Key key) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        int[] state = key.getCurrentDrawableState();
        if (key.codes[0] != 0) {
            drawable.setState(state);
        }
        drawable.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        drawable.draw(canvas);
    }

    //绘制文本
    private void drawText(Canvas canvas, Keyboard.Key key) {
        if(key.label != null){
            String label = key.label.toString();
            Field field;
            int keyTextSize;
            try {
                //获取KeyboardView设置的默认文本字体大小
                field = KeyboardView.class.getDeclaredField("mLabelTextSize");
                field.setAccessible(true);
                keyTextSize = (int) field.get(this);
                paint.setTextSize(keyTextSize);
                paint.setTypeface(Typeface.DEFAULT);
                paint.getTextBounds(label,0,label.length(),bounds);
                canvas.drawText(label,key.x + (key.width / 2), (key.y + key.height / 2) + bounds.height() / 2, paint);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void setKeyboardUtil(KeyboardUtil keyboardUtil) {
        this.keyboardUtil = keyboardUtil;
    }
}