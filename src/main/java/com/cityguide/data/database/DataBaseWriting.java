package com.cityguide.data.database;

import com.cityguide.core.Entity;

public interface DataBaseWriting<T extends Entity> {
    String exec(T entity);
}
