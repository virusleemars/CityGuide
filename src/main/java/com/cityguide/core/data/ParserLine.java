package com.cityguide.core.data;

public class ParserLine {
    private static final Integer LINE_KEY_POSITION = 0;
    private static final Integer LINE_DATA_POSITION = 1;
    private static final String LINE_SEPARATOR = " = ";

    private String key;
    private String data;

    ParserLine(String line){
        String[] data;
        data = line.split(LINE_SEPARATOR);
        this.key = data[LINE_KEY_POSITION];
        this.data = data[LINE_DATA_POSITION];
    }

    public String getKey()
    {
        return this.key;
    }

    String getData(){
        return this.data;
    }
}
