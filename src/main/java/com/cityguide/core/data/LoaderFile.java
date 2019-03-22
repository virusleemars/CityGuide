package com.cityguide.core.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class LoaderFile {
    private static final String RESOURCE_FILE = "resource/data.txt";
    private static final String OBJECT_START = "<data>";
    private static final String OBJECT_END = "</data>";

    private List<String> loadData() {
        BufferedReader bReader;
        List<String> list = new ArrayList<>();
        String line;

        try{
            bReader = new BufferedReader(new FileReader(RESOURCE_FILE));
            while ((line = bReader.readLine()) != null)
                list.add(line);
            bReader.close();
        }catch (Exception ex){
            System.out.println(RESOURCE_FILE + " IOError");
        }

        return list;
    }

    public List<ParserObject> parseFile() {
        List<String> listData = loadData();
        List<ParserObject> list = new ArrayList<>();
        ParserObject parserObject = null;
        boolean objectFind = false;

        // Объект не найден?
        for (String line : listData)
            if (!objectFind) {
                // Тег объект?
                if (line.compareTo(OBJECT_START) == 0) {
                    objectFind = true;
                }
            }
            // Найден объект
            else {
                // Информация об объекте обработана
                if (line.compareTo(OBJECT_END) == 0) {
                    list.add(parserObject);
                    objectFind = false;
                    parserObject = null;
                }
                // Сохранение информации об объекте
                else {
                    // Если объект не определен
                    if (parserObject == null) {
                        //  определить объект
                        switch (NameObject.valueOf( new ParserLine(line).getData()) ){
                            case CITY:
                                parserObject = new ParserObjectCity();
                                break;

                            case PLACE:
                                parserObject = new ParserObjectPlace();
                                break;

                            case COMMENT:
                                parserObject = new ParserObjectComment();
                                break;

                            default:
                                System.out.println("Error parser object!!!");
                                break;

                        }
                    }
                    parserObject.writeString(line);
                }

            }

        return list;
    }

}
