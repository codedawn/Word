/**
  * Copyright 2022 json.cn 
  */
package com.codedawn.word.json.booklist;
import java.util.List;

/**
 * Auto-generated: 2022-03-06 12:29:19
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class BookJsonRootBean {

    private String reason;
    private int code;
    private List<Cates> cates;
    public void setReason(String reason) {
         this.reason = reason;
     }
     public String getReason() {
         return reason;
     }

    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setCates(List<Cates> cates) {
         this.cates = cates;
     }
     public List<Cates> getCates() {
         return cates;
     }

}