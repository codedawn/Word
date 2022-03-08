/**
 * Copyright 2022 json.cn
 */
package com.codedawn.word.json.wordbook;

/**
 * Auto-generated: 2022-03-06 12:24:25
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class VocabularyJsonRootBean {

    private int wordRank;
    private String headWord;
    private Content content;
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

    public void setContent(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

}