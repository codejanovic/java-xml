package io.github.codejanovic.java.xml;

import io.github.codejanovic.java.xml.factories.Factory1;

import java.util.Iterator;

public class DecoratingIterator<T> implements Iterator<T> {
    private final Iterator<T> decorated;
    private final Factory1<T, T> factory;

    public DecoratingIterator(final Iterator<T> decorated, final Factory1<T, T> factory) {
        this.decorated = decorated;
        this.factory = factory;
    }

    @Override
    public boolean hasNext() {
        return decorated.hasNext();
    }

    @Override
    public T next() {
        return factory.provide(decorated.next());
    }
}
