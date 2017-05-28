package io.github.codejanovic.java.xml;


import java.util.Iterator;
import java.util.Optional;
import java.util.Stack;

public interface XmlElementIterator extends Iterator<XmlElement> {

    final class Features {
        private Features() {
        }

        public static XmlElementIterator flat(final XmlElement element) {
            return new Flat(element);
        }

        public static XmlElementIterator single(final XmlElement element) {
            return new Single(element);
        }

        public static XmlElementIterator recursive(final XmlElement element) {
            return new Flat(element);
        }

    }

    final class Flat implements XmlElementIterator {
        private final Iterator<XmlElement> iterator;

        public Flat(final XmlElement element) {
            this.iterator = element.elements().iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public XmlElement next() {
            return iterator.next();
        }
    }

    final class Single implements XmlElementIterator {
        private final XmlElement element;
        private Optional<XmlElement> next = Optional.empty();

        public Single(XmlElement element) {
            this.element = element;
        }

        @Override
        public boolean hasNext() {
            return !next.isPresent();
        }

        @Override
        public XmlElement next() {
            next = Optional.of(element);
            return element;
        }
    }

    final class Recursive implements XmlElementIterator {
        private final Stack<XmlElementIterator> iterators = new Stack<>();

        public Recursive(final XmlElement start) {
            this.iterators.add(new Single(start));
        }

        @Override
        public boolean hasNext() {
            if (!iterators.peek().hasNext())
                iterators.pop();
            return !iterators.empty() && iterators.peek().hasNext();
        }

        @Override
        public XmlElement next() {
            final XmlElement next = iterators.peek().next();
            this.iterators.add(new Flat(next));
            return next;
        }
    }
}
