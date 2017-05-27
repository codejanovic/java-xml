package io.github.codejanovic.java.xml.dom4j;

import io.github.codejanovic.java.xml.XmlAttribute;
import org.dom4j.Attribute;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public final class Dom4jXmlAttribute extends XmlAttribute.Abstract {
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
}
