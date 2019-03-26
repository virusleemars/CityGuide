package com.cityguide.core;

import java.util.List;

public interface Viewing {
    void showError(String error);

    void showListCity(List<City> list);
    void showListPlace(List<Place> list);
    void showListComment(List<Comment> list);

    void showCity(City city);
    void showPlace(Place place);
}
