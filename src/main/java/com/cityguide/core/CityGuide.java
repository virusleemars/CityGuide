package com.cityguide.core;


import com.cityguide.core.data.*;

import java.util.*;


public class CityGuide implements ICityGuide {

    private List<City> listCity;

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
    public City getCityByName(String name) {
        for (City c : listCity)
            if (c.getName().equalsIgnoreCase(name)){
                return c;
            }
        return null;
    }

    @Override
    public void addCity(City city) {

    }

    @Override
    public void addPlace(City city, Place place) {

    }

    @Override
    public void addComment(City city, Place place, Comment comment) {

    }

    public CityGuide() {
        listCity = new ArrayList<>();
        List<ParserObject> listObject = new LoaderFile().parseFile();
        City city;
        Place place;

        for (ParserObject object : listObject)
            switch (NameObject.valueOf(object.getMyType())) {
                case CITY:
                    city = new City(object.getMyName());
                    city.setId(object.getMyID());
                    ParserObjectCity parserObjectCity = (ParserObjectCity)object;
                    city.setCountry( parserObjectCity.getCityCountry());
                    city.setDescription( parserObjectCity.getCityDescription());
                    // Coordinates!!!!
                    listCity.add(city);
                    break;

                case PLACE:
                    if ((city = findCityByID(object.getMyIDParent())) != null) {
                        ParserObjectPlace parserObjectPlace = (ParserObjectPlace)object;
                        place = new Place(object.getMyName(), parserObjectPlace.getPlaceAddress());
                        place.setId(object.getMyID());
                        place.setIdParent(object.getMyIDParent());
                        place.setAnnotation(parserObjectPlace.getPlaceAnnotation());
                        // Coordinates!!!!
                        city.addPlace(place);
                    }
                    break;

                case COMMENT:
                    ParserObjectComment parserObjectComment = (ParserObjectComment)object;
                    if ((city = findCityByID(parserObjectComment.getIDCity())) != null)
                    if ((place = findPlaceByID(city, object.getMyIDParent())) != null){
                        Comment comment = new Comment();
                        comment.setName(object.getMyName());
                        comment.setId(object.getMyID());
                        comment.setIdParent(object.getMyIDParent());
                        comment.setRating(parserObjectComment.getRating());
                        //comment.setCalendar();
                        comment.setReview(parserObjectComment.getReview());
                        place.addComment(comment);
                    }

                    break;

                default:
                    System.out.println("ERROR type!!!");
                    break;
            }
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
