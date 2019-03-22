package com.cityguide;

import com.cityguide.core.City;
import com.cityguide.core.CityGuide;
import com.cityguide.core.Comment;
import com.cityguide.core.Place;
import com.cityguide.view.Viewer;

import java.util.List;

public class Application {

    public static void main(String[] args){
        System.out.println("Start programm....");

        CityGuide cityGuide = new CityGuide();
        Viewer viewer = new Viewer();

        List <City> list = cityGuide.getAllCity();
        viewer.showListCity(list);
/*
        City city = cityGuide.getCityByName("Москва");
        viewer.showCity(city);
*/
/*
        List<Place> listPlace = cityGuide.getAllPlace("Москва");
        viewer.showListPlace(listPlace);
*/

/*
        List<Comment> listComment = cityGuide.getAllComment("Москва", "красная площадь");
        viewer.showListComment(listComment);
*/
    }
}


