package com.urise.webapp.storage.strategy;

import java.io.IOException;

interface ThrowingConsumer<T, E extends IOException> {
    void accept(T t) throws E;
}