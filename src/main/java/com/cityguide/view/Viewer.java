package com.cityguide.view;

import com.cityguide.core.City;
import com.cityguide.core.Comment;
import com.cityguide.core.Place;

import java.util.List;

public class Viewer implements IViewer {

    @Override
    public void showAllPlace(City city) {
        System.out.println("*** PLACE ***");
        showListPlace(city.getPlaceList());
    }

    @Override
    public void showPlace(Place place) {
        System.out.println(place.getName());
        showAllComments(place);
    }

    @Override
    public void showAllComments(Place place) {
        System.out.println("*** COMMENT ***");
        if (place.getListComment().size() == 0) System.out.println(" no comments");
        showListComment( place.getListComment());
    }

    @Override
    public void showComment(Comment comment) {
        System.out.println(comment.getName() + " - " + comment.getReview());
    }

    @Override
    public void showCity(City city) {
        System.out.println("*** CITY ***");
        System.out.println(city.getName());
        System.out.println(city.getCountry());
//        System.out.println(city.getGeoCoord().toStringFormat());
        System.out.println(city.getDescription());
        showAllPlace(city);
    }

    @Override
    public void showListPlace(List<Place> list) {
        for (Place place : list){
            showPlace(place);
        }
    }

    @Override
    public void showListComment(List<Comment> list) {
        for (Comment comment : list){
            showComment(comment);
        }
    }

    @Override
    public void showListCity(List<City> list) {
        for (City city : list){
            showCity(city);
        }

    }
}
