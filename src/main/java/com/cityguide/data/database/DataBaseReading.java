package com.cityguide.data.database;

import com.cityguide.core.Entity;

import java.sql.ResultSet;

public interface DataBaseReading<T extends Entity> {
    T exec(ResultSet rs);
}

