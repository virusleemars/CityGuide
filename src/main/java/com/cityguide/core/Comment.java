package com.cityguide.core;

import java.util.Calendar;

// Отзыв о месте
public class Comment extends Entity {
    private static final byte MIN_RATING = 1;
    private static final byte MAX_RATING = 5;

    private int idCity;
    private byte rating;                        // Рейтинг (1 - 5)
    private Calendar calendar;                  // Дата и время
    private String review;                      // Отзыв

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

}