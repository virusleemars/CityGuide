package com.cityguide;

import com.cityguide.core.*;
import com.cityguide.data.filesystem.*;
import com.cityguide.view.Viewer;

import java.util.List;

public class Application {

    public static void main(String[] args){
        // Создать объекты хранилище данных
        Storing<City> storageCity = new StorageFileCity();
        Storing<Place> storagePlace = new StorageFilePlace();
        Storing<Comment> storageComment = new StorageFileComment();

        // Создать объект отображения данных
        Viewer viewer = new Viewer();

        // Создать объект путеводитель
        CityGuide cityGuide = new CityGuide();

        // Настроить объект путеводитель
        cityGuide.setStorage(storageCity, storagePlace, storageComment);
        cityGuide.setViewer(viewer);
        cityGuide.loadFromStorage();

        cityGuide.requestAllCity();
        cityGuide.requestCity("Москва");
        cityGuide.requestCity("Москва 1");
        cityGuide.requestPlacesCity("Ульяновск");
        cityGuide.requestPlaceCity("Москва", "Красная площадь");

        /*
        City newCity = new City("Ульяновск");
        newCity.setId(3);
        newCity.setCountry("Россия");
        newCity.setDescription("Город в европейской части России, административный центр Ульяновской области. Является городом областного значения, образует муниципальное образование город Ульяновск со статусом городского округа. Расположен на Приволжской возвышенности, на берегах рек Волги (Куйбышевское водохранилище) и Свияги, в месте сближения их русел. Находится в 890 км к востоку / юго-востоку от Москвы.");
        newCity.setGeoCoord(54.3282400,48.3865700);
        cityGuide.addCity(newCity);
        */

    }
}


