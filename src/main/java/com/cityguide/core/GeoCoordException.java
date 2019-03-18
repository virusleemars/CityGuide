package com.cityguide.core;

public class GeoCoordException extends Exception{
    private double fi;
    private double lamda;

    public double getFi() {
        return fi;
    }

    public double getLamda() {
        return lamda;
    }

    public GeoCoordException(String message, double f, double l){
        super(message);
        fi = f;
        lamda = l;
    }
}
