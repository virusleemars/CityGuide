package com.cityguide.view;

import com.cityguide.core.City;
import com.cityguide.core.Comment;
import com.cityguide.core.Place;

import java.util.List;

public interface IViewer {
    void showCity(City city);
    void showPlace(Place place);
    void showAllPlace(City city);
    void showComment(Comment comment);
    void showAllComments(Place place);
    void showListCity(List<City> list);
    void showListPlace(List<Place> list);
    void showListComment(List<Comment> list);

}
