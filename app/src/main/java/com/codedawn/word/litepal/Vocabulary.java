package com.codedawn.word.litepal;

import com.codedawn.word.json.wordbook.VocabularyJsonRootBean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Vocabulary extends LitePalSupport implements Serializable {

    @Column(unique = true)
    private int id;


    private String wordJson;


    private VocabularyJsonRootBean vocabularyJsonRootBean;

    private String wordHead;

    //学习等级
    /**
     *    0. 第一次学习
     *
     *    1. 第一个记忆周期：5分钟
     *
     * 　　2. 第二个记忆周期：30分钟
     *
     * 　　3. 第三个记忆周期：12小时
     *
     * 　　4. 第四个记忆周期：1天
     *
     * 　　5. 第五个记忆周期：2天
     *
     * 　　6. 第六个记忆周期：4天
     *
     * 　　7. 第七个记忆周期：7天
     *
     *    //可选
     * 　　8. 第八个记忆周期：15天
     */
    //默认是0
    private int level;

    //默认是0
    private long nextTime;

    //默认是0，0代表未学，1代表已学
    private int learned;

    //0是没收藏，1是收藏
    private int favorite;

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getWordJson() {
        return wordJson;
    }

    public void setWordJson(String wordJson) {
        this.wordJson = wordJson;
    }

    public VocabularyJsonRootBean getVocabularyJsonRootBean() {
        return vocabularyJsonRootBean;
    }

    public void setVocabularyJsonRootBean(VocabularyJsonRootBean vocabularyJsonRootBean) {
        this.vocabularyJsonRootBean = vocabularyJsonRootBean;
    }

    public String getWordHead() {
        return wordHead;
    }

    public void setWordHead(String wordHead) {
        this.wordHead = wordHead;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getNextTime() {
        return nextTime;
    }

    public void setNextTime(long nextTime) {
        this.nextTime = nextTime;
    }

    public int getLearned() {
        return learned;
    }

    public void setLearned(int learned) {
        this.learned = learned;
    }
}
