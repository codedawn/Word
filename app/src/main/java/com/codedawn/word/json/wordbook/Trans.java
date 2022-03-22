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
public class Trans implements Serializable {

    private String tranCn;
    private String descOther;
    private String pos;
    private String descCn;
    private String tranOther;
    public void setTranCn(String tranCn) {
         this.tranCn = tranCn;
     }
     public String getTranCn() {
         return tranCn;
     }

    public void setDescOther(String descOther) {
         this.descOther = descOther;
     }
     public String getDescOther() {
         return descOther;
     }

    public void setPos(String pos) {
         this.pos = pos;
     }
     public String getPos() {
         return pos;
     }

    public void setDescCn(String descCn) {
         this.descCn = descCn;
     }
     public String getDescCn() {
         return descCn;
     }

    public void setTranOther(String tranOther) {
         this.tranOther = tranOther;
     }
     public String getTranOther() {
         return tranOther;
     }

}