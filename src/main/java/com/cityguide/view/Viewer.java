package com.cityguide.view;

import com.cityguide.core.City;
import com.cityguide.core.Comment;
import com.cityguide.core.Viewing;
import com.cityguide.core.Place;

import java.util.List;

public class Viewer implements Viewing {

    public void showAllPlace(City city) {
        System.out.println();
        showListPlace(city.getPlaceList());
    }



    public void showAllComments(Place place) {
        System.out.println("*** COMMENT ***");
        if (place.getListComment().size() == 0) System.out.println(" no comments");
        showListComment( place.getListComment());
    }

    public void showComment(Comment comment) {
        System.out.println("[" + comment.getCalendar() + "] " + comment.getName() +
                            "\tОЦЕНКА: " + comment.getRating() + ": " + comment.getReview());
    }

    @Override
    public void showCity(City city) {
        System.out.println();
        System.out.println(city.getName());
        System.out.println(city.getCountry());
        System.out.println(city.getGeoCoord().toStringFormat());
        System.out.println(city.getDescription());
    }

    @Override
    public void showPlace(Place place) {
        System.out.println();
        System.out.println(place.getName());
        System.out.println(place.getAddress());
        System.out.println(place.getAnnotation());
        System.out.println(place.getGeoCoord().getString());
    }

    @Override
    public void showListPlace(List<Place> list) {
        System.out.println();
        System.out.println("Найдено мест: " + list.size());
        for (Place place : list){
            System.out.println(place.getName());
        }
    }

    public void showListComment(List<Comment> list) {
        System.out.println();
        for (Comment comment : list){
            showComment(comment);
        }
    }

    @Override
    public void showError(String error) {
        System.out.println();
        System.out.println("Error: " + error);
    }

    @Override
    public void showListCity(List<City> list) {
        System.out.println();
        System.out.println("Найдено городов: " + list.size());
        for (City city : list){
            System.out.println(city.getName());
        }

    }
}
