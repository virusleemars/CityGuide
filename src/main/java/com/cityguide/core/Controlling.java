package com.cityguide.core;

public interface Controlling {
    void requestAllCity();
    void requestCity(String nameCity);
    void requestPlacesCity(String nameCity);
    void requestPlaceCity(String nameCity, String namePlace);


    void setViewer(Viewing viewer);
    void setStorage(Storing<City> storageCity, Storing<Place> storagePlace, Storing<Comment> storageComment);
    void loadFromStorage();
}
