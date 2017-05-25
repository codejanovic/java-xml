package io.github.codejanovic.java.xml.dom4j;

import io.github.codejanovic.java.shortcuts.Shortcuts;
import io.github.codejanovic.java.xml.StreamIterable;
import io.github.codejanovic.java.xml.XmlAttribute;
import io.github.codejanovic.java.xml.XmlElement;
import io.github.codejanovic.java.xml.XmlElementIterator;
import org.dom4j.Element;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public final class Dom4JXmlElement implements XmlElement {
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
        return () -> new XmlElementIterator.Recursive(this);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(f("<%s", name()));
        attributes().forEach(attribute -> builder.append(Shortcuts.f(" %s", attribute.toString())));
        builder.append(f(">"));
        if (!value().trim().isEmpty())
            builder.append(f("%s", value()));
        elements().forEach(element -> builder.append(f("%s", element)));
        builder.append("\n");
        builder.append(f("</%s", name()));
        return builder.toString();
    }
}
