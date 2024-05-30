package com.quizapp;

import java.util.List;

public class Question {
    private String query;
    private List<String> options;
    private int answerkey;

    public Question(String query, List<String> options, int answerkey) {
        this.query = query;
        this.options = options;
        this.answerkey = answerkey;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getAnswerkey() {
        return answerkey;
    }

    public void setAnswerkey(int answerkey) {
        this.answerkey = answerkey;
    }

    @Override
    public String toString() {
        return "Question{" +
                "query='" + query + '\'' +
                ", options=" + options +
                ", answerkey=" + answerkey +
                '}';
    }
}
