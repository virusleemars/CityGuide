package com.cityguide.data.database;

import com.cityguide.core.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class StorageDB<T extends Entity> {
    private static final String DATA_BASE_DRIVER = "org.postgresql.Driver";
    private static final String DATA_BASE_URL = "jdbc:postgresql://localhost:5432/MyDB";
    private static final String DATA_BASE_LOGIN = "postgres";
    private static final String DATA_BASE_PASSWORD = "sysadm";

    private String nameTable;

    StorageDB(String nameTable) {
        this.nameTable = nameTable;
    }

    void createTable(String var){
        try {
            // Загрузить драйвер
            Class.forName(DATA_BASE_DRIVER);
            // Создать соединение с БД
            try (Connection con = DriverManager.getConnection(DATA_BASE_URL, DATA_BASE_LOGIN, DATA_BASE_PASSWORD)) {
                // Создать запрос
                Statement stmt = con.createStatement();
                // Выполнить запрос
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + nameTable + "(" + var + ")");
                // Освободить ресурсы запрос
                stmt.close();
            }
            // Закрыть соединение с БД
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<T> readAll(DataBaseReading<T> reader) {
        List<T> list = new ArrayList<>();
        try {
            // Загрузить драйвер
            Class.forName(DATA_BASE_DRIVER);
            // Создать соединение с БД
            try (Connection con = DriverManager.getConnection(DATA_BASE_URL, DATA_BASE_LOGIN, DATA_BASE_PASSWORD)) {
                // Создать запрос
                Statement stmt = con.createStatement();
                // Выполнить запрос
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + nameTable);
                // Обработка результата запроса
                while (rs.next()) {
                    // Создаем объект и заполняем его
                    list.add( reader.exec(rs) );
                }
                // Освободить ресурсы запрос и результат запроса
                rs.close();
                stmt.close();
            }
            // Закрыть соединение с БД
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    void writeAll(List<T> list, DataBaseWriting<T> writer) {
        try {
            // Загрузить драйвер
            Class.forName(DATA_BASE_DRIVER);
            // Создать соединение с БД
            try (Connection con = DriverManager.getConnection(DATA_BASE_URL, DATA_BASE_LOGIN, DATA_BASE_PASSWORD)) {
                // Создать запрос
                Statement stmt = con.createStatement();
                // Выполнить запрос - очистить таблицу
                stmt.executeUpdate("TRUNCATE TABLE " + nameTable);
                // Выполнить запрос - добавить из списка
                for (T element : list){
                    stmt.executeUpdate("INSERT INTO " + nameTable +
                            "(" + writer.exec(element) + ")");
                }
                // Освободить ресурсы запрос
                stmt.close();
            }
            // Закрыть соединение с БД
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
