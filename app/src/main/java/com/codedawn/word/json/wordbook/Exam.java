/**
 * Copyright 2022 json.cn
 */
package com.codedawn.word.json.wordbook;

import java.util.List;

/**
 * Auto-generated: 2022-03-06 12:24:25
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Exam {

    private String question;
    private Answer answer;
    private int examType;
    private List<Choices> choices;

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setExamType(int examType) {
        this.examType = examType;
    }

    public int getExamType() {
        return examType;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public List<Choices> getChoices() {
        return choices;
    }

}