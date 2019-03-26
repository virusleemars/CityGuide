package com.cityguide.data.filesystem;

import com.cityguide.core.Entity;

import java.util.ArrayList;
import java.util.List;

abstract class ParserObject <T extends Entity> {
    enum HeaderData {ID, ID_PARENT, NAME}

    private List<String> objectData;

    ParserObject(){
        objectData = new ArrayList<>();
    }

    List<String> getObjectData() {
        return objectData;
    }

    public void doEmpty(){
        objectData.clear();
        for (HeaderData headerData : HeaderData.values()){
            writeString( ParserLine.convert( headerData.name(), ""));
        }
    }

    void writeString(String data){
        objectData.add(data);
    }

    Integer getHeaderSize() {
        return HeaderData.NAME.ordinal() + 1;
    }

    private String getData(Integer offset) {
        return new ParserLine(objectData.get(offset)).getData();
    }

    String getMyName(){
        return getData(HeaderData.NAME.ordinal());
    }

    Integer getMyID(){
        return Integer.parseInt(getData(HeaderData.ID.ordinal()));
    }

    Integer getMyIDParent() {
        return Integer.parseInt(getData(HeaderData.ID_PARENT.ordinal()));
    }

    private void setData(HeaderData headerData, String data){
        objectData.set( headerData.ordinal(), ParserLine.convert( headerData.name(), data));
    }

    void setMyName(String name){
        setData( HeaderData.NAME, name);
    }

    void setMyID(Integer id){
        setData( HeaderData.ID, String.valueOf(id));
    }

    void setMyIDParent(Integer idParent){
        setData( HeaderData.ID_PARENT, String.valueOf(idParent));
    }

    public abstract T getEntity();

    public abstract void setEntity(T entity);

    @Override
    public String toString() {
        return "ParserObject{" +
                "objectData=" + objectData +
                '}';
    }
}
