package com.cityguide.core;

public interface Controlling {
    void requestAllCity();
    void requestCity(String nameCity);
    void requestPlacesCity(String nameCity);
    void requestPlaceCity(String nameCity, String namePlace);
    void requestPlaceComment(String nameCity, String namePlace);

    void addCity(String nameCity, String country, String description, double f, double l);
    void clearCity();
    void addPlace(String nameCity, String namePlace, String address, String annotation, double f, double l);
    void clearPlace(String nameCity);
    void addComment(String nameCity, String namePlace, String nameUser, String review, int rating, String data);
    void clearComment(String nameCity, String namePlace);


    void setViewer(Viewing viewer);
    void setStorage(Storing<City> storageCity, Storing<Place> storagePlace, Storing<Comment> storageComment);
    void loadFromStorage();
}
