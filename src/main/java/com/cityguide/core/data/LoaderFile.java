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

    public LoaderFile(List<ParserObject> list) {
        List<String> listData = loadData();
        ParserObject parserObject = null;
        boolean objectFind = false;

        for (String line : listData)
        {
            // Объект не найден?
            if (!objectFind) {
                // Тег объект?
                if (line.compareTo(OBJECT_START) == 0) {
                    parserObject = new ParserObject();
                    objectFind = true;
                }
            }
            // Найден объект
            else {
                // Информация об объекте обработана
                if (line.compareTo(OBJECT_END) == 0) {
                     list.add(parserObject);
                     objectFind = false;
                }
                // Сохранение информации об объекте
                else{
                    parserObject.writeData( line );
                }

            }
        }
    }

}
