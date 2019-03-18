package com.cityguide.core.data;

public class ParserObjectPlace {
    enum PlaceObjectData { ADDRESS, ANNOTATION, COORD}

    private ParserObject parserObject;

    public ParserObjectPlace(ParserObject parserObject) {
        this.parserObject = parserObject;
    }

    private String getData(PlaceObjectData place){
        return new ParserLine(parserObject.getObjectData().get(place.ordinal() +
                              parserObject.getSizeHeader())).getData();
    }
    public String getPlaceAddress(){
        return getData( PlaceObjectData.ADDRESS);
    }

    public String getPlaceAnnotation(){
        return getData( PlaceObjectData.ANNOTATION);
    }

    public String getPlaceCoord(){
        return getData( PlaceObjectData.COORD);
    }
}
