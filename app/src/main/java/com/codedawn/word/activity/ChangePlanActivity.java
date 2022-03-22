package com.codedawn.word.activity;

import androidx.annotation.NonNull;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.codedawn.word.R;
import com.codedawn.word.litepal.UserData;
import com.codedawn.word.json.wordbook.VocabularyJsonRootBean;
import com.codedawn.word.litepal.Vocabulary;
import com.codedawn.word.util.ConstantData;
import com.codedawn.word.util.FileUtil;
import com.codedawn.word.util.GsonUtil;
import com.codedawn.word.util.Okhttp;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 选中单词书后，进入当前页面，可以选择每天学习多少单词
 */
public class ChangePlanActivity extends BasicActivity {


    private EditText editText;

    private TextView textGo, textBook, textWordMaxNum;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_plan);
        init();

        initListener();

    }

    private void initListener() {
        textGo.setOnClickListener((v -> {
            // 开启等待框
            progressDialog = new ProgressDialog(ChangePlanActivity.this);
            progressDialog.setTitle("请稍等");
            progressDialog.setMessage("数据准备中...");
            progressDialog.setCancelable(false);
            progressDialog.show();

           downloadBook();
        }));
    }

    private void downloadBook() {
        //下载离线包
        Okhttp.request(UserData.getInstance().getBook().getOfflinedata(), new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                FileUtil.getFileByBytes(response.body().bytes(), getFilesDir()+ "/" + ConstantData.DIR_TOTAL, UserData.getInstance().getBook().getId()+".zip");
                FileUtil.unZipFile(getFilesDir()+ "/" + ConstantData.DIR_TOTAL + "/" + UserData.getInstance().getBook().getId()+".zip"
                        , getFilesDir() + "/" + ConstantData.DIR_TOTAL + "/" + ConstantData.DIR_AFTER_FINISH, false);

                String json = FileUtil.readLocalJson(ConstantData.DIR_TOTAL + "/" + ConstantData.DIR_AFTER_FINISH + "/" + UserData.getInstance().getBook().getId() + ".json");


                List<VocabularyJsonRootBean> vocabularyList = GsonUtil.parseJsonToVocabularyList(json);
                saveVocabulary(vocabularyList);

                Intent intent = new Intent(ChangePlanActivity.this, HomeActivity.class);
                startActivity(intent);
                progressDialog.dismiss();

            }
        });
    }

    //保存单词到数据库
    private void saveVocabulary(List<VocabularyJsonRootBean> vJsonList) {
        List<Vocabulary> vList = new ArrayList<>();
        LitePal.deleteAll("vocabulary");
        for (VocabularyJsonRootBean v : vJsonList) {
            Vocabulary vocabulary = new Vocabulary();
            vocabulary.setWordJson(GsonUtil.vocabularyJsonRootBeanToJson(v));
            vocabulary.setWordHead(v.getHeadWord());
            vocabulary.save();
            vList.add(vocabulary);
        }
    }
    private void init() {
        editText = findViewById(R.id.edit_word_num);
        textGo = findViewById(R.id.text_plan_next);
        textBook = findViewById(R.id.text_plan_chosen);
        textWordMaxNum = findViewById(R.id.text_max_word_num);
    }
}