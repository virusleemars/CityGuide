package com.cityguide.core;

import java.util.*;

public interface Storing<T> {
    List<T> readAll();
    void writeAll(List<T> list);
    T read(int index);
    void write(T element);
    T remove(int index);
}
