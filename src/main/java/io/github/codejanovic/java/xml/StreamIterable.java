package io.github.codejanovic.java.xml;

import io.github.codejanovic.java.xml.factories.Factory1;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface StreamIterable<T> extends Iterable<T> {
    default Stream<T> stream() {
        final int characteristics = Spliterator.DISTINCT | Spliterator.SORTED | Spliterator.ORDERED;
        final Spliterator<T> splitIterator = Spliterators.spliteratorUnknownSize(iterator(), characteristics);
        return StreamSupport.stream(splitIterator, false);
    }

    final class Decorating<T> implements StreamIterable<T> {
        private final StreamIterable<T> decorated;
        private final Factory1<T, T> factory;

        public Decorating(final StreamIterable<T> decorated, final Factory1<T, T> factory) {
            this.decorated = decorated;
            this.factory = factory;
        }

        @Override
        public Iterator<T> iterator() {
            return new DecoratingIterator<>(decorated.iterator(), factory);
        }
    }

    final class Empty<T> implements StreamIterable<T> {

        private final static Empty<Object> empty = new Empty<>();

        private Empty() {
        }

        @Override
        public Iterator<T> iterator() {
            return Collections.emptyIterator();
        }

        public static <T> StreamIterable<T> one() {
            return (StreamIterable<T>) empty;
        }
    }

    final class Fake<T> implements StreamIterable<T> {
        private final Collection<T> collection;

        public Fake(final Collection<T> collection) {
            this.collection = new ArrayList<>(collection);
        }

        public Fake() {
            this(Collections.emptyList());
        }

        public Fake with(final Collection<T> collection) {
            return new Fake<T>(collection);
        }

        @Override
        public Iterator<T> iterator() {
            return collection.iterator();
        }
    }
}
