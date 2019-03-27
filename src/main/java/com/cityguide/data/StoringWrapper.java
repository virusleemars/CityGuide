package com.cityguide.data;

import com.cityguide.core.Entity;
import com.cityguide.core.Storing;

import java.util.List;

public class StoringWrapper<T extends Entity> implements Storing<T> {
    private Storing<T> storing;
    private boolean isThread;

    public StoringWrapper(Storing<T> storing, boolean isThread ) {
        this.storing = storing;
        this.isThread = isThread;
    }

    @Override
    public List<T> readAll() {
        return storing.readAll();
    }

    @Override
    public void writeAll(List<T> list) {
        if (!isThread){
            storing.writeAll(list);
        } else {
             Thread thread = new Thread(() -> storing.writeAll(list));
             thread.start();
        }
    }
}
