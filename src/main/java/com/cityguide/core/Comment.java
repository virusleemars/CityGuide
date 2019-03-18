package com.cityguide.core;

import java.util.Calendar;

// Отзыв о месте
public class Comment {
    private static final byte MIN_RATING = 1;
    private static final byte MAX_RATING = 5;

    private int id;
    private int idParent;

    private String name;                        // Имя/ник оставившего отзыв
    private byte rating;                        // Рейтинг (1 - 5)
    private Calendar calendar;                  // Дата и время
    private String review;                      // Отзыв

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }
}
