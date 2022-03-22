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
public class RelWord implements Serializable {

    private String desc;
    private List<Rels> rels;
    public void setDesc(String desc) {
         this.desc = desc;
     }
     public String getDesc() {
         return desc;
     }

    public void setRels(List<Rels> rels) {
         this.rels = rels;
     }
     public List<Rels> getRels() {
         return rels;
     }

}