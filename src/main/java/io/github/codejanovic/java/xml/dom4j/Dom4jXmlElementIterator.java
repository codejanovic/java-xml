package io.github.codejanovic.java.xml.dom4j;

import io.github.codejanovic.java.xml.XmlElement;
import io.github.codejanovic.java.xml.XmlElementIterator;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

public final class Dom4jXmlElementIterator implements XmlElementIterator {
    private final Iterator dom4jIterator;

    public Dom4jXmlElementIterator(final Element element) {
        this.dom4jIterator = element.elementIterator();
    }

    public Dom4jXmlElementIterator(List list) {
        this.dom4jIterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return dom4jIterator.hasNext();
    }

    @Override
    public XmlElement next() {
        return new Dom4JXmlElement((Element) dom4jIterator.next());
    }
}
