package com.codedawn.word.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codedawn.word.R;
import com.codedawn.word.activity.WordDetailActivity;
import com.codedawn.word.litepal.Vocabulary;
import com.codedawn.word.util.WordApplication;

import java.util.List;
import java.util.zip.Inflater;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private List<Vocabulary> vocabularyList;

    public WordListAdapter(List<Vocabulary> vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        ImageView imgSearch;
        TextView textWord, textMean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imgSearch = itemView.findViewById(R.id.img_itls_search);
            textWord = itemView.findViewById(R.id.text_itls_word);
            textMean = itemView.findViewById(R.id.text_itls_mean);
        }
    }

    @NonNull
    @Override
    public WordListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vocabulary vocabulary = vocabularyList.get(position);
        holder.imgSearch.setOnClickListener(v -> {
            Intent intent = new Intent(holder.view.getContext(), WordDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("vocabulary", vocabulary);
            WordApplication.getContext().startActivity(intent);
        });
        holder.textWord.setText(vocabulary.getVocabularyJsonRootBean().getHeadWord());
        holder.textMean.setText(vocabulary.getVocabularyJsonRootBean().getOutcontent().getWord().getContent().getTrans().get(0).getDescCn());
    }



    @Override
    public int getItemCount() {
        return vocabularyList.size();
    }
}
