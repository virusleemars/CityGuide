package com.cityguide.core.data;

public class ParserObjectCity {
    enum CityObjectData { COUNTRY, DESCRIPTION, COORD}

    private ParserObject parserObject;

    public ParserObjectCity(ParserObject parserObject) {
        this.parserObject = parserObject;
    }

    private String getData(CityObjectData city){
        return new ParserLine(parserObject.getObjectData().get(city.ordinal() +
                              parserObject.getSizeHeader())).getData();
    }

    public String getCityCountry(){
        return getData( CityObjectData.COUNTRY);
    }

    public String getCityDescription(){
        return getData( CityObjectData.DESCRIPTION);
    }

    public String getCityCoord(){
        return getData( CityObjectData.COORD);
    }
}
