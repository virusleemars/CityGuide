package com.cityguide.core;


import com.cityguide.core.Exception.GeoCoordException;

import java.util.ArrayList;
import java.util.List;

public class Place extends Entity{
    private String address;
    private Float rating;
    private String annotation;
    private GeoCoord geoCoord;
    private List<Comment> listComment;

    public List<Comment> getListComment() {
        return listComment;
    }

    public Place(String name, String address) {
        setName(name);
        this.address = address;
        this.listComment = new ArrayList<>();
    }

    public void setGeoCoord(double f, double l) {
        this.geoCoord = new GeoCoord();
        try {
            this.geoCoord.setFL(f, l);
        } catch (GeoCoordException ex){
            System.out.println(ex.getMessage());
        }
    }

    public GeoCoord getGeoCoord() {
        return geoCoord;
    }

    void addComment(Comment comment){
        this.listComment.add(comment);
    }

    public String getAddress() {
        return address;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + getName() + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
