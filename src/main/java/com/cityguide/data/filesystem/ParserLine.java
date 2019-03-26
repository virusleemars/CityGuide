package com.cityguide.data.filesystem;

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

    public void setKey(String key) {
        this.key = key;
    }

    public void setData(String data) {
        this.data = data;
    }

    static String convert(String key, String data) {
        return key + LINE_SEPARATOR + data;
    }
}
