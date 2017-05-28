package io.github.codejanovic.java.xml.fakes;

import io.github.codejanovic.java.xml.XmlAttribute;

public final class FakeXmlAttribute extends XmlAttribute.Abstract {
    private final String name;
    private final String value;

    public FakeXmlAttribute(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public FakeXmlAttribute() {
        this("", "");
    }

    public FakeXmlAttribute withName(final String name) {
        return new FakeXmlAttribute(name, value);
    }

    public FakeXmlAttribute withValue(final String value) {
        return new FakeXmlAttribute(name, value);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String value() {
        return value;
    }
}
