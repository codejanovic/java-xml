package io.github.codejanovic.java.xml.fakes;

import io.github.codejanovic.java.xml.XmlAttribute;
import org.jusecase.builders.Builder;

public final class FakeXmlAttributeBuilder implements Builder<XmlAttribute> {
    private final String name;
    private final String value;

    public FakeXmlAttributeBuilder(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public FakeXmlAttributeBuilder(String name) {
        this(name, "");
    }

    public static FakeXmlAttributeBuilder attribute(String name, String value) {
        return new FakeXmlAttributeBuilder(name, value);
    }

    public static FakeXmlAttributeBuilder attribute(String name) {
        return new FakeXmlAttributeBuilder(name);
    }

    @Override
    public XmlAttribute build() {
        return new FakeXmlAttribute(name, value);
    }
}
