package com.cityguide.core.data;

public class ParserObjectPlace extends ParserObject {
    enum PlaceObjectData { ADDRESS, ANNOTATION, COORD}

    String getData(Integer offset){
        return new ParserLine(getObjectData().get(offset + getHeaderSize())).getData();
    }

    public String getPlaceAddress(){
        return getData( PlaceObjectData.ADDRESS.ordinal());
    }

    public String getPlaceAnnotation(){
        return getData( PlaceObjectData.ANNOTATION.ordinal());
    }

    public String getPlaceCoord(){
        return getData( PlaceObjectData.COORD.ordinal());
    }
}
