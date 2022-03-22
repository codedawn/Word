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
public class Choices implements Serializable {

    private int choiceIndex;
    private String choice;
    public void setChoiceIndex(int choiceIndex) {
         this.choiceIndex = choiceIndex;
     }
     public int getChoiceIndex() {
         return choiceIndex;
     }

    public void setChoice(String choice) {
         this.choice = choice;
     }
     public String getChoice() {
         return choice;
     }

}