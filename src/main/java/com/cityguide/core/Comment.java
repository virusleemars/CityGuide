package com.cityguide.core;

import java.util.Calendar;

// Отзыв о месте
public class Comment extends Entity {
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 5;

    private int idCity;
    private int rating;                        // Рейтинг (1 - 5)
    private String calendar;                  // Дата и время
    private String review;                      // Отзыв

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}