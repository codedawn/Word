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
public class SourceInfo implements Serializable {

    private String paper;
    private String level;
    private String year;
    private String type;
    public void setPaper(String paper) {
         this.paper = paper;
     }
     public String getPaper() {
         return paper;
     }

    public void setLevel(String level) {
         this.level = level;
     }
     public String getLevel() {
         return level;
     }

    public void setYear(String year) {
         this.year = year;
     }
     public String getYear() {
         return year;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

}