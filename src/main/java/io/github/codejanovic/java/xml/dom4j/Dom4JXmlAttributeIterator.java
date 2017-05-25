package io.github.codejanovic.java.xml.dom4j;

import io.github.codejanovic.java.xml.XmlAttribute;
import io.github.codejanovic.java.xml.XmlAttributeIterator;
import io.github.codejanovic.java.xml.dom4j.Dom4jXmlAttribute;
import org.dom4j.Attribute;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

public final class Dom4JXmlAttributeIterator implements XmlAttributeIterator {
    private final Iterator dom4jIterator;

    public Dom4JXmlAttributeIterator(final Element element) {
        this.dom4jIterator = element.attributeIterator();
    }

    public Dom4JXmlAttributeIterator(List list) {
        this.dom4jIterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return dom4jIterator.hasNext();
    }

    @Override
    public XmlAttribute next() {
        return new Dom4jXmlAttribute((Attribute) dom4jIterator.next());
    }
}
