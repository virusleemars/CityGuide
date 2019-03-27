package com.cityguide;

import com.cityguide.core.*;
import com.cityguide.data.StoringWrapper;
import com.cityguide.data.database.*;
import com.cityguide.data.filesystem.*;
import com.cityguide.view.Viewer;

public class Application {

    public static void main(String[] args){
        // Создать объекты хранилище данных
        //StoringWrapper<City> storingWrapperCity = new StoringWrapper<>(new StorageFileCity(), true);
        StoringWrapper<City> storingWrapperCity = new StoringWrapper<>(new StorageDBCity(), true);
        //StoringWrapper<Place> storingWrapperPlace = new StoringWrapper<>(new StorageFilePlace(), true);
        StoringWrapper<Place> storingWrapperPlace = new StoringWrapper<>(new StorageDBPlace(), true);
        //StoringWrapper<Comment> storingWrapperComment = new StoringWrapper<>(new StorageFileComment(), true);
        StoringWrapper<Comment> storingWrapperComment = new StoringWrapper<>(new StorageDBComment(), true);

        Storing<Comment> storageComment = new StorageFileComment();
        //Storing<Comment> storageComment = new StorageDBComment();

        // Создать объект отображения данных
        Viewer viewer = new Viewer();

        // Создать объект путеводитель
        CityGuide cityGuide = new CityGuide();

        // Настроить объект путеводитель
        cityGuide.setStorage(storingWrapperCity, storingWrapperPlace, storingWrapperComment);
        cityGuide.setViewer(viewer);
        cityGuide.loadFromStorage();

        cityGuide.clearCity();
        cityGuide.addCity("Ульяновск", "Россия", "Город в европейской части России, административный центр Ульяновской области", 54.32824, 48.38657);
        cityGuide.addCity("Санкт-Петербург", "Россия", "Столица номер 2", 59.93863, 30.31413);
        cityGuide.addCity("Москва", "Россия", "Столица нашей страны", 55.75222, 37.61556);

//        cityGuide.clearPlace("Москва");
//        cityGuide.clearPlace("Ульяновск");
//        cityGuide.clearPlace("Санкт-Петербург");

//        cityGuide.addPlace("Москва", "Красная Площадь", "расположена между Московским Кремлём (к западу) и Китай-городом (на восток)", "Общая длина — 330 метров, ширина — 75 метров, площадь — 24 750 м². Вымощена брусчаткой из крымского габбро-диабаза", 55.753575, 37.62104);

        cityGuide.requestAllCity();

        cityGuide.requestCity("Москва");
        cityGuide.requestCity("Ульяновск");
        cityGuide.requestCity("Санкт-Петербург");


//        cityGuide.requestPlacesCity("Москва");
//        cityGuide.requestPlaceCity("Москва", "Красная Площадь");

/*
        cityGuide.clearComment("Москва", "Красная площадь");
        cityGuide.addComment("Москва", "Красная площадь", "Вася Пупкин",
                "Люблю гулять по красной площади", 5, "03.05.07 11:00");
        cityGuide.addComment("Москва", "Красная площадь", "Пиарщик",
                "Никогда не ходите туда гулять", 2, "06.01.09 22:00");
        cityGuide.requestPlaceComment("Москва", "Красная площадь");
*/

    }
}


