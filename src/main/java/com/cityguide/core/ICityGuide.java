package com.cityguide.core;

import java.util.List;

public interface ICityGuide {
    List<City> getAllCity();
    List<Place> getAllPlace(String nameCity);
    List<Comment> getAllComment(String nameCity, String namePlace);
    City getCityByName(String name);
    void addCity(City city);
    void addPlace(City city, Place place);
    void addComment(City city, Place place, Comment comment);

}
