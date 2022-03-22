/**
  * Copyright 2022 json.cn 
  */
package com.codedawn.word.json.wordbook;

import java.io.Serializable;

/**
 * Auto-generated: 2022-03-11 15:35:58
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Word implements Serializable {

    private String wordHead;
    private String wordId;
    private Content content;
    public void setWordHead(String wordHead) {
         this.wordHead = wordHead;
     }
     public String getWordHead() {
         return wordHead;
     }

    public void setWordId(String wordId) {
         this.wordId = wordId;
     }
     public String getWordId() {
         return wordId;
     }

    public void setContent(Content content) {
         this.content = content;
     }
     public Content getContent() {
         return content;
     }

}