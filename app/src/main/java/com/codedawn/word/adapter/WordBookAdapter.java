package com.codedawn.word.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codedawn.word.R;
import com.codedawn.word.activity.HomeActivity;
import com.codedawn.word.json.booklist.Book;
import com.codedawn.word.json.booklist.BookJsonRootBean;
import com.codedawn.word.util.WordApplication;

import java.util.List;

public class WordBookAdapter extends RecyclerView.Adapter<WordBookAdapter.ViewHolder> {


    private List<Book> bookList;

    public WordBookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookList.get(position);
        Glide.with(WordApplication.getContext()).load(book.getCover()).into(holder.imgBook);
        holder.textBookName.setText(book.getTitle());
        holder.textBookSource.setText(book.getBookOrigin().getOriginName());
        holder.textBookWordNum.setText(String.valueOf(book.getWordNum()));
        initListener(holder.view,book);
    }

    private void initListener(View view,Book book) {
        view.setOnClickListener(v -> {
            Intent intent = new Intent(WordApplication.getContext(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            WordApplication.getContext().startActivity(intent);
            Toast.makeText(WordApplication.getContext(), book.getTitle(), Toast.LENGTH_SHORT).show();
        });


    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView imgBook;
        TextView textBookName, textBookSource, textBookWordNum;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgBook = itemView.findViewById(R.id.item_img_book);
            textBookName = itemView.findViewById(R.id.item_text_book_name);
            textBookSource = itemView.findViewById(R.id.item_text_book_source);
            textBookWordNum = itemView.findViewById(R.id.item_text_book_word_num);
        }

    }
}
