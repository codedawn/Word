/**
  * Copyright 2022 json.cn 
  */
package com.codedawn.word.json.wordbook;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2022-03-11 15:35:58
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Content implements Serializable {

    private Sentence sentence;
    private RealExamSentence realExamSentence;
    private String usphone;
    private String ukspeech;
    private int star;
    private String usspeech;
    private String picture;
    private List<Exam> exam;
    private Syno syno;
    private String ukphone;
    private Phrase phrase;
    private String phone;
    private String speech;
    private RemMethod remMethod;
    private RelWord relWord;
    private List<Trans> trans;
    public void setSentence(Sentence sentence) {
         this.sentence = sentence;
     }
     public Sentence getSentence() {
         return sentence;
     }

    public void setRealExamSentence(RealExamSentence realExamSentence) {
         this.realExamSentence = realExamSentence;
     }
     public RealExamSentence getRealExamSentence() {
         return realExamSentence;
     }

    public void setUsphone(String usphone) {
         this.usphone = usphone;
     }
     public String getUsphone() {
         return usphone;
     }

    public String getUkspeech() {
        return ukspeech;
    }

    public void setUkspeech(String ukspeech) {
        this.ukspeech = ukspeech;
    }

    public String getUsspeech() {
        return usspeech;
    }

    public void setUsspeech(String usspeech) {
        this.usspeech = usspeech;
    }

    public void setPicture(String picture) {
         this.picture = picture;
     }
     public String getPicture() {
         return picture;
     }

    public void setExam(List<Exam> exam) {
         this.exam = exam;
     }
     public List<Exam> getExam() {
         return exam;
     }

    public void setSyno(Syno syno) {
         this.syno = syno;
     }
     public Syno getSyno() {
         return syno;
     }

    public void setUkphone(String ukphone) {
         this.ukphone = ukphone;
     }
     public String getUkphone() {
         return ukphone;
     }

    public void setPhrase(Phrase phrase) {
         this.phrase = phrase;
     }
     public Phrase getPhrase() {
         return phrase;
     }

    public void setPhone(String phone) {
         this.phone = phone;
     }
     public String getPhone() {
         return phone;
     }

    public void setSpeech(String speech) {
         this.speech = speech;
     }
     public String getSpeech() {
         return speech;
     }

    public void setRemMethod(RemMethod remMethod) {
         this.remMethod = remMethod;
     }
     public RemMethod getRemMethod() {
         return remMethod;
     }

    public void setRelWord(RelWord relWord) {
         this.relWord = relWord;
     }
     public RelWord getRelWord() {
         return relWord;
     }

    public void setTrans(List<Trans> trans) {
         this.trans = trans;
     }
     public List<Trans> getTrans() {
         return trans;
     }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}