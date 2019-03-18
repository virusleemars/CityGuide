package com.cityguide.core;


import com.cityguide.core.data.*;

import java.util.*;


public class CityGuide implements ICityGuide {
    private static final String NAME_OBJECT_CITY = "CITY";
    private static final String NAME_OBJECT_PLACE = "PLACE";
    private static final String NAME_OBJECT_COMMENT = "COMMENT";

    private List<City> listCity;

    public CityGuide() {
        listCity = new ArrayList<>();
        List<ParserObject> listObject = new ArrayList<>();
        new LoaderFile(listObject);
        City city;
        Place place;

        for (ParserObject object : listObject)
            switch (object.getMyType()) {
                case NAME_OBJECT_CITY:
                    city = new City(object.getMyName());
                    city.setId(object.getMyID());
                    ParserObjectCity parserObjectCity = new ParserObjectCity(object);
                    city.setCountry( parserObjectCity.getCityCountry());
                    city.setDescription( parserObjectCity.getCityDescription());
                    // Coordinates!!!!
                    listCity.add(city);
                    break;

                case NAME_OBJECT_PLACE:
                    if ((city = findCityByID(object.getMyIDParent())) != null) {
                        ParserObjectPlace parserObjectPlace = new ParserObjectPlace(object);
                        place = new Place(object.getMyName(), parserObjectPlace.getPlaceAddress());
                        place.setId(object.getMyID());
                        place.setIdParent(object.getMyIDParent());
                        place.setAnnotation(parserObjectPlace.getPlaceAnnotation());
                        // Coordinates!!!!
                        city.addPlace(place);
                    }
                    break;

                case NAME_OBJECT_COMMENT:
                    ParserObjectComment parserObjectComment = new ParserObjectComment(object);
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
                        System.out.println("TYTA");
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

    @Override
    public void getCity(String name) {

    }

    public List<City> getListCity() {
        return listCity;
    }
}
