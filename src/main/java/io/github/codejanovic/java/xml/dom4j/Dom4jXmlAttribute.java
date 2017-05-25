package io.github.codejanovic.java.xml.dom4j;

import io.github.codejanovic.java.xml.XmlAttribute;
import org.dom4j.Attribute;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public final class Dom4jXmlAttribute implements XmlAttribute {
    private final Attribute attribute;

    public Dom4jXmlAttribute(final Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public String name() {
        return attribute.getName();
    }

    @Override
    public String value() {
        return attribute.getStringValue();
    }

    @Override
    public String toString() {
        return f("%s=\"%s\"", name(), value());
    }
}
