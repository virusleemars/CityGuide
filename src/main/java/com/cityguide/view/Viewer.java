package com.cityguide.view;

import com.cityguide.core.City;
import com.cityguide.core.Comment;
import com.cityguide.core.Place;

public class Viewer implements IViewer {
    @Override
    public void showCity(City city) {
        System.out.println("*** CITY ***");
        System.out.println(city.getName());
        System.out.println(city.getCountry());
//        System.out.println(city.getGeoCoord().toStringFormat());
        System.out.println(city.getDescription());
        System.out.println("*** PLACE ***");
        for (Place place : city.getPlaceList()){
            System.out.println(place.getName());
            System.out.println("*** COMMENT ***");
            if (place.getListComment().size() == 0) System.out.println(" no comments");
            for (Comment comment: place.getListComment()){
                System.out.println(comment.getName() + " - " + comment.getReview());

            }
        }
    }
}
