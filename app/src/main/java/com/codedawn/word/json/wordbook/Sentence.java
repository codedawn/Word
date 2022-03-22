/**
  * Copyright 2022 json.cn 
  */
package com.codedawn.word.json.wordbook;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2022-03-11 15:35:58
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Sentence implements Serializable {

    private List<Sentences> sentences;
    private String desc;
    public void setSentences(List<Sentences> sentences) {
         this.sentences = sentences;
     }
     public List<Sentences> getSentences() {
         return sentences;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

}