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
public class RealExamSentence implements Serializable {

    private List<RealSentences> sentences;
    private String desc;
    public void setSentences(List<RealSentences> sentences) {
         this.sentences = sentences;
     }
     public List<RealSentences> getSentences() {
         return sentences;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

}