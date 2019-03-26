package com.cityguide.data.filesystem;

import com.cityguide.core.Place;

public class ParserObjectPlace extends ParserObject<Place> {
    enum PlaceObjectData { ADDRESS, ANNOTATION, COORD}

    private String getData(Integer offset){
        return new ParserLine(getObjectData().get(offset + getHeaderSize())).getData();
    }

    private void setData(PlaceObjectData placeObjectData, String data){
        getObjectData().set( placeObjectData.ordinal() + getHeaderSize(),
                ParserLine.convert( placeObjectData.name(), data));
    }
    public void doEmpty(){
        super.doEmpty();
        for (PlaceObjectData placeObjectData : PlaceObjectData.values())
            writeString(ParserLine.convert(placeObjectData.name(), ""));
    }

    private String getPlaceAddress(){
        return getData( PlaceObjectData.ADDRESS.ordinal());
    }

    private void setPlaceAddress(String address){
        setData(PlaceObjectData.ADDRESS, address);
    }

    private String getPlaceAnnotation(){
        return getData( PlaceObjectData.ANNOTATION.ordinal());
    }

    private void setPlaceAnnotation(String annotation){
        setData(PlaceObjectData.ANNOTATION, annotation);
    }

    private String getPlaceCoord(){
        return getData( PlaceObjectData.COORD.ordinal());
    }

    private void setPlaceCoord(String coord) {setData(PlaceObjectData.COORD, coord);}

    @Override
    public Place getEntity() {
        Place place = new Place(super.getMyName(), this.getPlaceAddress());
        place.setId(super.getMyID());
        place.setIdParent(super.getMyIDParent());
        place.setAnnotation(this.getPlaceAnnotation());
        String[] coordList = this.getPlaceCoord().split(", ");
        place.setGeoCoord(Double.parseDouble(coordList[0]), Double.parseDouble(coordList[1]));
        return place;
    }

    @Override
    public void setEntity(Place entity) {
        this.doEmpty();
        super.setMyID(entity.getId());
        super.setMyName(entity.getName());
        super.setMyIDParent(entity.getIdParent());
        this.setPlaceAddress(entity.getAddress());
        this.setPlaceAnnotation(entity.getAnnotation());
        this.setPlaceCoord(entity.getGeoCoord().getString());
    }
}
