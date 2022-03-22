package com.codedawn.word.litepal;

import com.codedawn.word.json.booklist.Book;
import com.codedawn.word.json.booklist.BookJsonRootBean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class CurrentBook extends LitePalSupport {

    @Column(unique = true)
    private int id;

    private String bookJson;

    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookJson() {
        return bookJson;
    }

    public void setBookJson(String bookJson) {
        this.bookJson = bookJson;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
