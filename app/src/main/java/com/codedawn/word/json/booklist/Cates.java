/**
  * Copyright 2022 json.cn 
  */
package com.codedawn.word.json.booklist;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Auto-generated: 2022-03-06 12:29:19
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Cates {

    private String cateName;
    @SerializedName("bookList")
    private List<Book> book;
    public void setCateName(String cateName) {
         this.cateName = cateName;
     }
     public String getCateName() {
         return cateName;
     }

    public void setBookList(List<Book> book) {
         this.book = book;
     }
     public List<Book> getBookList() {
         return book;
     }

}