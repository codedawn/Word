/**
  * Copyright 2022 json.cn 
  */
package com.codedawn.word.json.wordbook;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Auto-generated: 2022-03-11 15:35:58
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class VocabularyJsonRootBean implements Serializable {

    private int wordRank;
    private String headWord;
    @SerializedName("content")
    private Outcontent Outcontent;
    private String bookId;
    public void setWordRank(int wordRank) {
         this.wordRank = wordRank;
     }
     public int getWordRank() {
         return wordRank;
     }

    public void setHeadWord(String headWord) {
         this.headWord = headWord;
     }
     public String getHeadWord() {
         return headWord;
     }

    public void setOutcontent(Outcontent Outcontent) {
         this.Outcontent = Outcontent;
     }
     public Outcontent getOutcontent() {
         return Outcontent;
     }

    public void setBookId(String bookId) {
         this.bookId = bookId;
     }
     public String getBookId() {
         return bookId;
     }

}