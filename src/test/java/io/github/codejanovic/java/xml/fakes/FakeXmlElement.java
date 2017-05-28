package io.github.codejanovic.java.xml.fakes;

import io.github.codejanovic.java.xml.StreamIterable;
import io.github.codejanovic.java.xml.XmlAttribute;
import io.github.codejanovic.java.xml.XmlElement;

public final class FakeXmlElement extends XmlElement.Abstract {
    private final String name;
    private final String value;
    private final StreamIterable<XmlAttribute> attributes;

    public FakeXmlElement(String name, String value, StreamIterable<XmlAttribute> attributes) {
        this.name = name;
        this.value = value;
        this.attributes = attributes;
    }

    public FakeXmlElement(String name, String value) {
        this(name, value, StreamIterable.Features.empty());
    }

    public FakeXmlElement() {
        this("", "", StreamIterable.Features.empty());
    }

    public FakeXmlElement withName(final String name) {
        return new FakeXmlElement(name, value, attributes);
    }

    public FakeXmlElement withValue(final String value) {
        return new FakeXmlElement(name, value, attributes);
    }

    public FakeXmlElement withAttributes(final StreamIterable<XmlAttribute> attributes) {
        return new FakeXmlElement(name, value, attributes);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public StreamIterable<XmlAttribute> attributes() {
        return attributes;
    }

    @Override
    public StreamIterable<XmlElement> elements() {
        return StreamIterable.Features.empty();
    }

    @Override
    public StreamIterable<XmlElement> recursive() {
        return StreamIterable.Features.empty();
    }
}
