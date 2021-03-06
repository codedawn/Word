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
public class Synos implements Serializable {

    private String pos;
    private String tran;
    private List<Hwds> hwds;
    public void setPos(String pos) {
         this.pos = pos;
     }
     public String getPos() {
         return pos;
     }

    public void setTran(String tran) {
         this.tran = tran;
     }
     public String getTran() {
         return tran;
     }

    public void setHwds(List<Hwds> hwds) {
         this.hwds = hwds;
     }
     public List<Hwds> getHwds() {
         return hwds;
     }

}