package com.cityguide.data.filesystem;

import com.cityguide.core.Place;
import com.cityguide.core.Storing;

import java.util.ArrayList;
import java.util.List;

public class StorageFilePlace implements Storing<Place> {
    private static final String RESOURCE_PLACE_FILE = "resource/place.txt";

    private ParserObjectFile<Place> parserObjectFile = new ParserObjectFile<>(RESOURCE_PLACE_FILE);

    @Override
    public List<Place> readAll() {
        List<Place> list = new ArrayList<>();
        parserObjectFile.loadParseObjects( ParserObjectPlace::new );
        for ( ParserObject<Place> objectPlace: parserObjectFile.getList()){
            Place place = objectPlace.getEntity();
            list.add(place);
        }
        return list;
    }

    @Override
    synchronized public void writeAll(List<Place> list) {
        parserObjectFile.clear();
        for (Place place : list){
            ParserObjectPlace parserObjectPlace = new ParserObjectPlace();
            parserObjectPlace.setEntity(place);
            parserObjectFile.add(parserObjectPlace);
        }
        parserObjectFile.saveParserObjects();
    }
}
