package io.github.codejanovic.java.xml;


import java.util.Iterator;
import java.util.Optional;
import java.util.Stack;

public interface XmlElementIterator extends Iterator<XmlElement> {

    final class Childs implements XmlElementIterator {
        private final Iterator<XmlElement> iterator;

        public Childs(final XmlElement element) {
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
            this.iterators.add(new Childs(next));
            return next;
        }
    }
}
