package com.cityguide.core;

import java.util.*;

public class City {
    private int id;
    private String name;                        // Название города
    private String country;                     // Страна
    private String description;                 // Краткое описание города
    private GeoCoord geoCoord;                  // Географические координаты
    private List<Place> placeList;              // Список мест города

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public GeoCoord getGeoCoord() {
        return geoCoord;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }



    public City(String name) {
        this.name = name;
        placeList = new ArrayList<Place>();
    }

    public void addPlace(Place place){
        placeList.add(place);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setGeoCoord(double f, double l) {
        this.geoCoord = new GeoCoord();
        try {
            this.geoCoord.setFL(f, l);
        } catch (GeoCoordException ex){
            System.out.println(ex.getMessage());
        }

    }

    public void printPlace(){
        for (Place p : placeList)
        {
            System.out.println(p);
        }
    }

}
