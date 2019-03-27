package com.cityguide.core;

import java.util.*;

public interface Storing<T> {
    List<T> readAll();
    void writeAll(List<T> list);
}
