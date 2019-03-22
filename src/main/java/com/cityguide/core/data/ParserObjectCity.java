package com.cityguide.core.data;

public class ParserObjectCity extends ParserObject {
    enum CityObjectData { COUNTRY, DESCRIPTION, COORD}

    @Override
    String getData(Integer offset){
        return new ParserLine(getObjectData().get(offset + getHeaderSize())).getData();
    }

    public String getCityCountry(){
        return getData( CityObjectData.COUNTRY.ordinal());
    }

    public String getCityDescription(){
        return getData( CityObjectData.DESCRIPTION.ordinal());
    }

    public String getCityCoord(){
        return getData( CityObjectData.COORD.ordinal());
    }
}
