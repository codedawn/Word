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
import com.codedawn.word.activity.ChangePlanActivity;
import com.codedawn.word.litepal.UserData;
import com.codedawn.word.json.booklist.Book;
import com.codedawn.word.litepal.CurrentBook;
import com.codedawn.word.util.GsonUtil;
import com.codedawn.word.util.WordApplication;

import java.util.List;

/**
 * 单词书列表适配器
 */
public class WordBookListAdapter extends RecyclerView.Adapter<WordBookListAdapter.ViewHolder> {


    private List<Book> bookList;

    public WordBookListAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_list, parent, false);
        return new ViewHolder(view);
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
        //点击书籍进行跳转
        view.setOnClickListener(v -> {
            Intent intent = new Intent(WordApplication.getContext(), ChangePlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            WordApplication.getContext().startActivity(intent);
            //设置当前用户选的书
            CurrentBook currentBook = new CurrentBook();
            currentBook.setBook(book);
            currentBook.setBookJson(GsonUtil.bookToJson(book));
            UserData.getInstance().setCurrentBook(currentBook);
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
            imgBook = itemView.findViewById(R.id.image_cover);
            textBookName = itemView.findViewById(R.id.item_text_book_name);
            textBookSource = itemView.findViewById(R.id.item_text_book_source);
            textBookWordNum = itemView.findViewById(R.id.item_text_book_word_num);
        }

    }
}
