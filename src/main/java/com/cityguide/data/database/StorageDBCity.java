package com.cityguide.data.database;

import com.cityguide.core.City;
import com.cityguide.core.Storing;

import java.sql.*;
import java.util.List;

public class StorageDBCity extends StorageDB<City> implements Storing<City> {
    private static final String DATA_BASE_TABLE = "CITY";

    enum TableCityCol{id, name, id_parent, country, description, geocoord}

    public StorageDBCity() {
        super(DATA_BASE_TABLE);
    }

    public void createTable(){
        String var =
                TableCityCol.id.name() + " INT PRIMARY KEY     NOT NULL," +
                        TableCityCol.id_parent.name() + " INT," +
                        TableCityCol.name.name() + " TEXT," +
                        TableCityCol.country.name() + " TEXT," +
                        TableCityCol.description.name() + " TEXT," +
                        TableCityCol.geocoord.name() + " TEXT";
        super.createTable(var);
    }

    @Override
    public List<City> readAll() {
        return super.readAll(this::createPlace);
    }

    private City createPlace(ResultSet resultSet)  {
        City city = null;
        try {
            city = new City(resultSet.getString(TableCityCol.name.name()));
            city.setId(resultSet.getInt(TableCityCol.id.name()));
            city.setIdParent(resultSet.getInt(TableCityCol.id_parent.name()));
            city.setDescription(resultSet.getString(TableCityCol.description.name()));
            city.setCountry(resultSet.getString(TableCityCol.country.name()));
            String[] coordList = resultSet.getString(TableCityCol.geocoord.name()).split(", ");
            city.setGeoCoord(Double.parseDouble(coordList[0]), Double.parseDouble(coordList[1]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    private String createSqlByPlace(City city){
        String id = String.valueOf(city.getId());
        String id_parent = String.valueOf(city.getIdParent());
        return  TableCityCol.id.name() +", " + TableCityCol.name.name() + ", " +
                TableCityCol.id_parent.name() + ", " + TableCityCol.country.name() +", " +
                TableCityCol.description.name() + ", " + TableCityCol.geocoord.name() + ") " +
                "VALUES (" + id + ", '" + city.getName() + "', " +
                id_parent + ", '" + city.getCountry() + "', '" +
                city.getDescription() + "', '" + city.getGeoCoord().getString() + "'";
    }

    @Override
    synchronized public void writeAll(List<City> list) {
        super.writeAll(list, this::createSqlByPlace);
    }
}
