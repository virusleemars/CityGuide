package com.cityguide.core.data;

import java.util.*;

public class ParserObject {
    enum HeaderData {TYPE, ID, ID_PARENT, NAME}

    private List<String> objectData;

    ParserObject() {
        objectData = new ArrayList<>();
    }

    List<String> getObjectData() {
        return objectData;
    }

    private String getData(HeaderData header){
        return new ParserLine(objectData.get(header.ordinal())).getData();
    }

    Integer getSizeHeader(){
        HeaderData header = HeaderData.NAME;
        return header.ordinal() + 1;
    }

    public String getMyType(){
        return getData(HeaderData.TYPE);
    }

    public String getMyName(){
        return getData(HeaderData.NAME);
    }

    public Integer getMyID(){
        return Integer.parseInt(getData(HeaderData.ID));
    }

    public Integer getMyIDParent(){
        return Integer.parseInt(getData(HeaderData.ID_PARENT));
    }

    void writeData(String data){
        objectData.add(data);
    }

}
