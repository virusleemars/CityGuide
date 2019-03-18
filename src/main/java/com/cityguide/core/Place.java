package com.cityguide.core;


import java.util.ArrayList;
import java.util.List;

public class Place {
    private int id;
    private int idParent;

    private String name;
    private String address;
    private Float rating;
    private String annotation;
    private GeoCoord geoCoord;
    private List<Comment> listComment;

    public List<Comment> getListComment() {
        return listComment;
    }

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
        this.listComment = new ArrayList<>();
    }

    public void setGeoCoord(GeoCoord geoCoord) {
        this.geoCoord = geoCoord;
    }

    public GeoCoord getGeoCoord() {
        return geoCoord;
    }

    public void addComment(Comment comment){
        this.listComment.add(comment);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
