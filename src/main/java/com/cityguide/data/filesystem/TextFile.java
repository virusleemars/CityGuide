package com.cityguide.data.filesystem;

import java.util.*;
import java.io.*;

public class TextFile {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    TextFile(String name) {
        this.name = name;
    }

    List<String> load() {
        BufferedReader bReader;
        List<String> list = new ArrayList<>();
        String line;
        try{
            bReader = new BufferedReader(new FileReader(name));
            while ((line = bReader.readLine()) != null)
                list.add(line);
            bReader.close();
        }catch (Exception ex){
            System.out.println(name + " IOError");
        }
        return list;
    }

    void save(List<String> list) {
        BufferedWriter bWriter;
        try{
            bWriter = new BufferedWriter(new FileWriter(name));
            for (String line : list){
                bWriter.write(line + '\n');
            }
            bWriter.close();
        }catch (Exception ex){
            System.out.println(name + " IOError");
        }
    }

}
