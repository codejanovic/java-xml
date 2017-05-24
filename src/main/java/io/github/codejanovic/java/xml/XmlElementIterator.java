package io.github.codejanovic.java.xml;


import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

public interface XmlElementIterator extends Iterator<XmlElement> {

    final class Dom4j implements XmlElementIterator {
        private final Iterator dom4jIterator;

        public Dom4j(final Element element) {
            this.dom4jIterator = element.elementIterator();
        }

        public Dom4j(List list) {
            this.dom4jIterator = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return dom4jIterator.hasNext();
        }

        @Override
        public XmlElement next() {
            return new XmlElement.Dom4J((Element) dom4jIterator.next());
        }
    }
}
