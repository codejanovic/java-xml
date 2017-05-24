package io.github.codejanovic.java.xml;

import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

public interface XmlAttributeIterator extends Iterator<XmlAttribute> {

    final class Dom4J implements XmlAttributeIterator {
        private final Iterator dom4jIterator;

        public Dom4J(final Element element) {
            this.dom4jIterator = element.attributeIterator();
        }

        public Dom4J(List list) {
            this.dom4jIterator = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return dom4jIterator.hasNext();
        }

        @Override
        public XmlAttribute next() {
            return new XmlAttribute.Dom4j((Attribute) dom4jIterator.next());
        }
    }
}
