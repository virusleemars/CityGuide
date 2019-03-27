package com.cityguide.data.database;

import com.cityguide.core.Place;
import com.cityguide.core.Storing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StorageDBPlace extends StorageDB<Place> implements Storing<Place> {
    private static final String DATA_BASE_TABLE = "PLACE";

    enum TablePlaceCol{id, name, id_parent, address, annotation, geocoord}

    public StorageDBPlace() {
        super(DATA_BASE_TABLE);
    }

    public void createTable(){
        String var =
                TablePlaceCol.id.name() + " INT PRIMARY KEY     NOT NULL," +
                        TablePlaceCol.id_parent.name() + " INT," +
                        TablePlaceCol.name.name() + " TEXT," +
                        TablePlaceCol.address.name() + " TEXT," +
                        TablePlaceCol.annotation.name() + " TEXT," +
                        TablePlaceCol.geocoord.name() + " TEXT";
        super.createTable(var);
    }

    @Override
    public List<Place> readAll() {
        return super.readAll(this::createPlace);
    }

    private Place createPlace(ResultSet resultSet)  {
        Place place = null;
        try {
            place = new Place(resultSet.getString(TablePlaceCol.name.name()),
                    resultSet.getString(TablePlaceCol.address.name()));
            place.setId(resultSet.getInt(TablePlaceCol.id.name()));
            place.setIdParent(resultSet.getInt(TablePlaceCol.id_parent.name()));
            place.setAnnotation(resultSet.getString(TablePlaceCol.annotation.name()));
            String[] coordList = resultSet.getString(TablePlaceCol.geocoord.name()).split(", ");
            place.setGeoCoord(Double.parseDouble(coordList[0]), Double.parseDouble(coordList[1]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return place;
    }

    private String createSqlByPlace(Place place){
        String id = String.valueOf(place.getId());
        String id_parent = String.valueOf(place.getIdParent());
        return TablePlaceCol.id.name() +", " + TablePlaceCol.name.name() + ", " +
                TablePlaceCol.id_parent.name() + ", " + TablePlaceCol.address.name() +", " +
                TablePlaceCol.annotation.name() + ", " + TablePlaceCol.geocoord.name() + ") " +
                "VALUES (" + id + ", '" + place.getName() + "', " +
                id_parent + ", '" + place.getAddress() + "', '" +
                place.getAnnotation() + "', '" + place.getGeoCoord().getString() + "'";
    }

    @Override
    synchronized public void writeAll(List<Place> list) {
        super.writeAll(list, this::createSqlByPlace);
    }
}
