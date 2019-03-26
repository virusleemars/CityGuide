package com.cityguide.core;

import java.util.*;


public class CityGuide implements Controlling {
    private Storing<City> storageCity;
    private Storing<Place> storagePlace;
    private Storing<Comment> storageComment;
    private Viewing viewer;
    private List<City> listCity = new ArrayList<>();

    @Override
    public void setViewer(Viewing viewer) {
        this.viewer = viewer;
    }

    @Override
    public void setStorage(Storing<City> storageCity, Storing<Place> storagePlace, Storing<Comment> storageComment) {
        this.storageCity = storageCity;
        this.storagePlace = storagePlace;
        this.storageComment = storageComment;
    }

    @Override
    public void loadFromStorage() {
        City city;
        listCity = storageCity.readAll();
        List<Place> places = storagePlace.readAll();
        List<Comment> comments = storageComment.readAll();
        for (Place place : places){
            if ( (city = findCityByID(place.getIdParent())) != null ){
                city.addPlace(place);
            }
        }
        for (Comment comment : comments) {
            if ( (city = findCityByID(comment.getIdCity())) != null ){
                Place place;
                if ( (place = findPlaceByID( city, comment.getIdParent())) != null ){
                    place.addComment(comment);
                }
            }
        }
    }

    @Override
    public void requestAllCity() {
        viewer.showListCity(listCity);
    }

    @Override
    public void requestCity(String nameCity) {
        City city = getCityByName(nameCity);
        if (city != null){
            viewer.showCity(city);
        }
        else{
            viewer.showError(nameCity + " not found");
        }
    }

    @Override
    public void requestPlacesCity(String nameCity) {
        City city = getCityByName(nameCity);
        if (city != null){
            viewer.showListPlace(city.getPlaceList());
        }
        else{
            viewer.showError(nameCity + " not found");
        }
    }

    @Override
    public void requestPlaceCity(String nameCity, String namePlace) {
        City city = getCityByName(nameCity);
        if (city != null){
           Place place = getPlaceByName(city, namePlace);
           if (place != null){
                viewer.showPlace(place);
            }
            else{
               viewer.showError(namePlace + " not found");
            }
        }else{
            viewer.showError(nameCity + " not found");
        }
    }

    /*
        @Override
        public List<City> getAllCity() {
            return listCity;
        }

        @Override
        public List<Place> getAllPlace(String nameCity) {
            City city = getCityByName(nameCity);
            return city.getPlaceList();
        }

        @Override
        public List<Comment> getAllComment(String nameCity, String namePlace) {
            List<Place> list = getAllPlace(nameCity);
            for (Place place : list){
                if (place.getName().equalsIgnoreCase(namePlace)){
                    return place.getListComment();
                }
            }
            return null;
        }

        @Override


        @Override
        public void addCity(City city) {
            if ( findCityByID(city.getId() ) == null){
                listCity.add(city);
                storageCity.writeAll(listCity);
            }
        }

        @Override
        public void addPlace(City city, Place place) {

        }

        @Override
        public void addComment(City city, Place place, Comment comment) {

        }
    */
    private City getCityByName(String name) {
        for (City c : listCity)
            if (c.getName().equalsIgnoreCase(name)){
              return c;
            }
        return null;
    }

    private Place getPlaceByName(City city, String name) {
        for (Place p : city.getPlaceList())
            if (p.getName().equalsIgnoreCase(name)){
                return p;
            }
        return null;
    }

    private City findCityByID(int id){
        for (City c : listCity){
            if (c.getId() == id) return c;
        }
        return null;
    }

    private Place findPlaceByID(City city, int id){
        for ( Place p : city.getPlaceList()){
            if (p.getId() == id)
                return p;
        }
        return null;
    }


}
