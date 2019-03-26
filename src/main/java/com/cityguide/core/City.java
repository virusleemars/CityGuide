package com.cityguide.core;

import com.cityguide.core.Exception.GeoCoordException;
import java.util.*;

public class City extends Entity{
    private String country;                     // Страна
    private String description;                 // Краткое описание города
    private GeoCoord geoCoord;                  // Географические координаты
    private List<Place> placeList;              // Список мест города

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
                "name='" + getName() + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public City(String name) {
        setName(name);
        placeList = new ArrayList<>();
    }

    void addPlace(Place place){
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
}
