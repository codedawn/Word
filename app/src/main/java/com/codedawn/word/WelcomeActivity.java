package com.codedawn.word;


import android.content.Intent;
import android.os.Bundle;

import com.codedawn.word.activity.BasicActivity;
import com.codedawn.word.activity.HomeActivity;
import com.codedawn.word.activity.KeyboardSpellActivity;

public class WelcomeActivity extends BasicActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
//        SQLiteDatabase db = Connector.getDatabase();


    }



}