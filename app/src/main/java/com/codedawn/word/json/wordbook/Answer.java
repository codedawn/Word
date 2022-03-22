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
public class Answer implements Serializable {

    private String explain;
    private int rightIndex;
    public void setExplain(String explain) {
         this.explain = explain;
     }
     public String getExplain() {
         return explain;
     }

    public void setRightIndex(int rightIndex) {
         this.rightIndex = rightIndex;
     }
     public int getRightIndex() {
         return rightIndex;
     }

}