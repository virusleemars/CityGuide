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
    public void writeAll(List<Place> list) {
        parserObjectFile.clear();
        for (Place place : list){
            ParserObjectPlace parserObjectPlace = new ParserObjectPlace();
            parserObjectPlace.setEntity(place);
            parserObjectFile.add(parserObjectPlace);
        }
        parserObjectFile.saveParserObjects();
    }

    @Override
    public Place read(int index) {
        ParserObject<Place> parserObjectPlace = parserObjectFile.get(index);
        return parserObjectPlace.getEntity();
    }

    @Override
    public void write(Place element) {
        ParserObjectPlace parserObjectPlace = new ParserObjectPlace();
        parserObjectPlace.setEntity(element);
        parserObjectFile.add(parserObjectPlace);
        parserObjectFile.saveParserObjects();
    }

    @Override
    public Place remove(int index) {
        ParserObject<Place> parserObjectPlace = parserObjectFile.remove(index);
        return parserObjectPlace.getEntity();
    }
}
