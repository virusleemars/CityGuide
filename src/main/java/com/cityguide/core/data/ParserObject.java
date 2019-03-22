package com.cityguide.core.data;

import java.util.ArrayList;
import java.util.List;

public abstract class ParserObject {
    enum HeaderData {TYPE, ID, ID_PARENT, NAME}

    private List<String> objectData;

    ParserObject(){
        objectData = new ArrayList<>();
    }

    List<String> getObjectData() {
        return objectData;
    }

    void writeString(String data){
        objectData.add(data);
    }

    abstract String getData(Integer offset);

    Integer getHeaderSize() {
        return HeaderData.NAME.ordinal() + 1;
    }

    private String getHeader(Integer offset) {
        return new ParserLine(objectData.get(offset)).getData();
    }

    public String getMyType(){
        return getHeader(HeaderData.TYPE.ordinal());
    }

    public String getMyName(){
        return getHeader(HeaderData.NAME.ordinal());
    }

    public Integer getMyID(){
        return Integer.parseInt(getHeader(HeaderData.ID.ordinal()));
    }

    public Integer getMyIDParent() {
        return Integer.parseInt(getHeader(HeaderData.ID_PARENT.ordinal()));
    }
}
