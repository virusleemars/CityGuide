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
            if ( (city = getCityByID(place.getIdParent())) != null ){
                city.addPlace(place);
            }
        }
        for (Comment comment : comments) {
            if ( (city = getCityByID(comment.getIdCity())) != null ){
                Place place;
                if ( (place = getPlaceByID( city, comment.getIdParent())) != null ){
                    place.addComment(comment);
                }
            }
        }
    }

    private List<Comment> getAllComments(){
        List<Comment> list = new ArrayList<>();
        for (City city : listCity){
            for (Place place : city.getPlaceList()){
                list.addAll(place.getListComment());
            }
        }
        return list;
    }

    private List<Place> getAllPlaces(){
        List<Place> list = new ArrayList<>();
        for (City city : listCity){
            list.addAll( city.getPlaceList());
        }
        return list;
    }

    @Override
    public void addPlace(String nameCity, String namePlace,  String address, String annotation, double f, double l) {
        City city = getCityByName(nameCity);
        if (city != null){
            if (getPlaceByName(city, namePlace) == null) {
                Place place = new Place(namePlace, address);
                place.setAnnotation(annotation);
                place.setId(city.getPlaceList().size());
                place.setIdParent(city.getId());
                place.setGeoCoord(f, l);
                city.addPlace(place);
                storagePlace.writeAll(getAllPlaces());
            }
        }
    }

    @Override
    public void clearPlace(String nameCity) {
        City city = getCityByName(nameCity);
        if (city != null){
            city.getPlaceList().clear();
            storagePlace.writeAll(getAllPlaces());
        }
    }

    @Override
    public void clearCity() {
        listCity.clear();
        storageCity.writeAll(listCity);
    }

    @Override
    public void addCity(String nameCity, String country, String description, double f, double l) {
        City city;
        if ((getCityByName(nameCity)) == null) {
            city = new City(nameCity);
            city.setCountry(country);
            city.setDescription(description);
            city.setGeoCoord(f, l);
            city.setIdParent(0);
            city.setId(listCity.size());
            listCity.add(city);
            storageCity.writeAll(listCity);
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

    @Override
    public void requestPlaceComment(String nameCity, String namePlace) {
        City city = getCityByName(nameCity);
        if (city != null) {
            Place place = getPlaceByName(city, namePlace);
            if (place != null) {
                viewer.showListComment(place.getListComment());
            }
        }
    }

    @Override
    public void addComment(String nameCity, String namePlace, String nameUser, String review, int rating, String data) {
        City city = getCityByName(nameCity);
        if (city != null) {
            Place place = getPlaceByName(city, namePlace);
            if (place != null) {
                Comment comment = new Comment();
                comment.setId(place.getListComment().size());
                comment.setIdParent(place.getId());
                comment.setIdCity(city.getId());
                comment.setName(nameUser);
                comment.setReview(review);
                comment.setRating(rating);
                comment.setCalendar(data);
                place.addComment(comment);
                storageComment.writeAll(getAllComments());
            }
        }
    }

    @Override
    public void clearComment(String nameCity, String namePlace) {
        City city = getCityByName(nameCity);
        if (city != null){
            Place place = getPlaceByName(city, namePlace);
            if (place != null){
                place.getListComment().clear();
                storageComment.writeAll(getAllComments());
            }
        }
    }

    private City getCityByName(String name) {
        for (City c : listCity)
            if (c.getName().equalsIgnoreCase(name)){
              return c;
            }
        return null;
    }

    private Place getPlaceByName(City city, String name) {
        for (Place p : city.getPlaceList()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    private City getCityByID(int id){
        for (City c : listCity){
            if (c.getId() == id) return c;
        }
        return null;
    }

    private Place getPlaceByID(City city, int id){
        for ( Place p : city.getPlaceList()){
            if (p.getId() == id)
                return p;
        }
        return null;
    }


}
