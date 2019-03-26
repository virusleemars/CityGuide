package com.cityguide.data.filesystem;

import com.cityguide.core.City;
import com.cityguide.core.Storing;

import java.util.ArrayList;
import java.util.List;

public class StorageFileCity implements Storing<City> {
    private static final String RESOURCE_CITY_FILE = "resource/city.txt";

    private ParserObjectFile<City> parserObjectFile = new ParserObjectFile<>(RESOURCE_CITY_FILE);

    @Override
    public List<City> readAll() {
        List<City> list = new ArrayList<>();
        parserObjectFile.loadParseObjects( ParserObjectCity::new );
        for ( ParserObject<City> objectCity: parserObjectFile.getList()){
            City city = objectCity.getEntity();
            list.add(city);
        }
        return list;
    }

    @Override
    public void writeAll(List<City> list) {
        parserObjectFile.clear();
        for (City city : list){
            ParserObjectCity parserObjectCity = new ParserObjectCity();
            parserObjectCity.setEntity(city);
            parserObjectFile.add(parserObjectCity);
        }
        parserObjectFile.saveParserObjects();
    }

    @Override
    public City read(int index) {
        ParserObject<City> parserObjectCity = parserObjectFile.get(index);
        return parserObjectCity.getEntity();
    }

    @Override
    public void write(City element) {
        ParserObjectCity parserObjectCity = new ParserObjectCity();
        parserObjectCity.setEntity(element);
        parserObjectFile.add(parserObjectCity);
        parserObjectFile.saveParserObjects();
    }

    @Override
    public City remove(int index) {
        ParserObject<City> parserObjectCity = parserObjectFile.remove(index);
        return parserObjectCity.getEntity();
    }
}
