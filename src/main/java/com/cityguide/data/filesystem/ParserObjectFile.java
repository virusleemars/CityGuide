package com.cityguide.data.filesystem;

import com.cityguide.core.Entity;

import java.util.*;

class ParserObjectFile<T extends Entity> extends TextFile {
    private static final String OBJECT_START = "<data>";
    private static final String OBJECT_END = "</data>";

    private List<ParserObject<T>> list = new ArrayList<>();

    ParserObjectFile(String name) {
        super(name);
    }

    void add(ParserObject<T> parserObject){
        list.add(parserObject);
    }

    ParserObject<T> get(int index) {return list.get(index);}

    ParserObject<T> remove(int index){ return list.remove(index);}

    void clear(){
        list.clear();
    }

    public List<ParserObject<T>> getList() {
        return list;
    }

    void loadParseObjects(ParserObjectCreator<T> creator){
        ParserObject<T> parserObject = null;
        boolean objectFound = false;
        List<String> listData = super.load();

        for (String line : listData)
            // Объект не найден?
            if (!objectFound) {
                // Тег объект?
                if (line.compareTo(OBJECT_START) == 0) {
                    objectFound = true;
                    parserObject = creator.create();
                }
            }
            // Найден объект
            else {
                // Информация об объекте обработана
                if (line.compareTo(OBJECT_END) == 0) {
                    list.add(parserObject);
                    objectFound = false;
                    parserObject = null;
                }
                // Сохранение информации об объекте
                else {
                    parserObject.writeString(line);
                }

            }
    }

    void saveParserObjects(){
        List<String> listSave = new ArrayList<>();
        for (ParserObject<T> obj : list){
            listSave.add(OBJECT_START);
            listSave.addAll(obj.getObjectData());
            listSave.add(OBJECT_END);
        }
        super.save(listSave);
    }

}
