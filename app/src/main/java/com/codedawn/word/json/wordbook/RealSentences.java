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
public class RealSentences implements Serializable {

    private String sContent;
    private SourceInfo sourceInfo;
    public void setSContent(String sContent) {
         this.sContent = sContent;
     }
     public String getSContent() {
         return sContent;
     }

    public void setSourceInfo(SourceInfo sourceInfo) {
         this.sourceInfo = sourceInfo;
     }
     public SourceInfo getSourceInfo() {
         return sourceInfo;
     }

}