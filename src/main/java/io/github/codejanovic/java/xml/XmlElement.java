package io.github.codejanovic.java.xml;

import org.dom4j.Element;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public interface XmlElement extends XmlAttribute {
    Iterable<XmlAttribute> attributes();
    Iterable<XmlElement> elements();

    final class Dom4J implements XmlElement {
        private final Element element;

        public Dom4J(Element element) {
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
        public Iterable<XmlAttribute> attributes() {
            return () -> new XmlAttributeIterator.Dom4J(element);
        }

        @Override
        public Iterable<XmlElement> elements() {
            return () -> new XmlElementIterator.Dom4j(element);
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder();
            builder.append(f("<%s", name()));
            attributes().forEach(attribute -> builder.append(f(" %s", attribute.toString())));
            builder.append(f(">"));
            if (!value().trim().isEmpty())
                builder.append(f("%s", value()));
            elements().forEach(element -> builder.append(f("%s", element)));
            builder.append("\n");
            builder.append(f("</%s", name()));
            return builder.toString();
        }
    }
}
