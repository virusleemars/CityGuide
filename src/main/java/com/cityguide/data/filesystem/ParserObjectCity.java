package com.cityguide.data.filesystem;

import com.cityguide.core.City;

public class ParserObjectCity extends ParserObject<City> {
    enum CityObjectData { COUNTRY, DESCRIPTION, COORD}

    private String getData(Integer offset){
        return new ParserLine(getObjectData().get(offset + getHeaderSize())).getData();
    }

    private void setData(CityObjectData cityObjectData, String data){
        getObjectData().set( cityObjectData.ordinal() + getHeaderSize(),
                                ParserLine.convert( cityObjectData.name(), data));
        }

    public void doEmpty(){
        super.doEmpty();
        for (CityObjectData cityObjectData: CityObjectData.values()){
            writeString( ParserLine.convert( cityObjectData.name(), ""));
        }
    }

    private String getCityCountry(){
        return getData( CityObjectData.COUNTRY.ordinal());
    }

    private void setCityCountry(String country){
        setData(CityObjectData.COUNTRY, country);
    }

    private String getCityDescription(){
        return getData( CityObjectData.DESCRIPTION.ordinal());
    }

    private void setCityDescription(String description){
        setData(CityObjectData.DESCRIPTION, description);
    }

    private String getCityCoord(){
        return getData( CityObjectData.COORD.ordinal());
    }

    private void setCityCoord(String coord){
        setData(CityObjectData.COORD, coord);
    }

    @Override
    public City getEntity() {
        City city = new City( super.getMyName());
        city.setId( super.getMyID());
        city.setCountry( this.getCityCountry());
        city.setDescription( this.getCityDescription());
        String[] coordList = this.getCityCoord().split(", ");
        city.setGeoCoord(Double.parseDouble(coordList[0]), Double.parseDouble(coordList[1]));
        return city;
    }

    @Override
    public void setEntity(City entity) {
        this.doEmpty();
        super.setMyID(entity.getId());
        super.setMyName(entity.getName());
        super.setMyIDParent( 0 );
        this.setCityCountry(entity.getCountry());
        this.setCityDescription(entity.getDescription());
        this.setCityCoord(entity.getGeoCoord().getString());
    }

}
