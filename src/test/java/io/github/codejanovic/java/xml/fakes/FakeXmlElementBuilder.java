package io.github.codejanovic.java.xml.fakes;

import io.github.codejanovic.java.xml.StreamIterable;
import io.github.codejanovic.java.xml.XmlAttribute;
import io.github.codejanovic.java.xml.XmlElement;
import org.jusecase.builders.Builder;

import java.util.ArrayList;
import java.util.List;

public final class FakeXmlElementBuilder implements Builder<XmlElement> {
    private final String name;
    private final String value;
    private List<XmlAttribute> attributes = new ArrayList<>();

    public FakeXmlElementBuilder(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public FakeXmlElementBuilder(String name) {
        this(name, "");
    }

    public static FakeXmlElementBuilder element(String name, String value) {
        return new FakeXmlElementBuilder(name, value);
    }

    public static FakeXmlElementBuilder element(String name) {
        return new FakeXmlElementBuilder(name);
    }

    public FakeXmlElementBuilder with(XmlAttribute attribute) {
        this.attributes.add(attribute);
        return this;
    }

    @Override
    public XmlElement build() {
        return new FakeXmlElement(name, value, StreamIterable.Features.eager(attributes));
    }
}
