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
public class Rels implements Serializable {

    private String pos;
    private List<Words> words;
    public void setPos(String pos) {
         this.pos = pos;
     }
     public String getPos() {
         return pos;
     }

    public void setWords(List<Words> words) {
         this.words = words;
     }
     public List<Words> getWords() {
         return words;
     }

}