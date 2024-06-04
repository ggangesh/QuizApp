package com.quizapp.display;

public class Result {
    private int userId;
    private int score;

    // Constructor, getters, and setters
    public Result(int userId, int score) {
        this.userId = userId;
        this.score = score;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

