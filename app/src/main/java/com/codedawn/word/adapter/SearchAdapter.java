package com.codedawn.word.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codedawn.word.R;
import com.codedawn.word.activity.WordDetailActivity;
import com.codedawn.word.json.wordbook.Word;
import com.codedawn.word.litepal.Vocabulary;
import com.codedawn.word.util.WordApplication;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


    private List<Vocabulary> vocabularyList;

    public SearchAdapter(List<Vocabulary> vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Word word = vocabularyList.get(position).getVocabularyJsonRootBean().getOutcontent().getWord();
        holder.textWord.setText(word.getWordHead());
        holder.textPhone.setText(word.getContent().getUkphone());
        holder.textTrans.setText(word.getContent().getTrans().get(0).getTranCn());
        //跳转单词详情页
        holder.view.setOnClickListener(v -> {
            Intent intent = new Intent(holder.view.getContext(), WordDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("vocabulary", vocabularyList.get(position));
            WordApplication.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return vocabularyList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView textWord, textTrans, textPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textWord = itemView.findViewById(R.id.text_search_word);
            textTrans = itemView.findViewById(R.id.text_search_trans);
            textPhone = itemView.findViewById(R.id.text_search_phone);
        }

     }


}
