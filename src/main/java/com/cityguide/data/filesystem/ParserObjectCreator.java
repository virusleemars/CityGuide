package com.cityguide.data.filesystem;

import com.cityguide.core.Entity;

public interface ParserObjectCreator<T extends Entity> {
    ParserObject<T> create();
}
