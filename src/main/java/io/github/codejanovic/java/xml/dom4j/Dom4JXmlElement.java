package io.github.codejanovic.java.xml.dom4j;

import io.github.codejanovic.java.xml.StreamIterable;
import io.github.codejanovic.java.xml.XmlAttribute;
import io.github.codejanovic.java.xml.XmlElement;
import io.github.codejanovic.java.xml.XmlElementIterator;
import org.dom4j.Element;

public final class Dom4JXmlElement extends XmlElement.Abstract {
    private final Element element;

    public Dom4JXmlElement(Element element) {
        this.element = element;
    }

    @Override
    public String name() {
        return element.getName();
    }

    @Override
    public String value() {
        return element.getText();
    }

    @Override
    public StreamIterable<XmlAttribute> attributes() {
        return () -> new Dom4JXmlAttributeIterator(element);
    }

    @Override
    public StreamIterable<XmlElement> elements() {
        return () -> new Dom4jXmlElementIterator(element);
    }

    @Override
    public StreamIterable<XmlElement> recursive() {
        return () -> XmlElementIterator.Features.recursive(this);
    }
}
