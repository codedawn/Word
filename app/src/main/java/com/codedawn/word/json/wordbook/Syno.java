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
public class Syno implements Serializable {

    private List<Synos> synos;
    private String desc;
    public void setSynos(List<Synos> synos) {
         this.synos = synos;
     }
     public List<Synos> getSynos() {
         return synos;
     }

    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

}