package com.cityguide;

import com.cityguide.core.City;
import com.cityguide.core.CityGuide;
import com.cityguide.view.Viewer;

import java.util.List;

public class Application {

    public static void main(String[] args){
        System.out.println("Start programm....");

        CityGuide cityGuide = new CityGuide();
        Viewer viewer = new Viewer();

        List <City> list = cityGuide.getListCity();
        for (City city : list){
            viewer.showCity(city);
            System.out.println("---------------------------------");
        }
    }
}


