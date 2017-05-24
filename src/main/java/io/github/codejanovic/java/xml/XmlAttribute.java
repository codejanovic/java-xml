package io.github.codejanovic.java.xml;


import org.dom4j.Attribute;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public interface XmlAttribute {
    String name();
    String value();

    final class Dom4j implements XmlAttribute {
        private final Attribute attribute;

        public Dom4j(final Attribute attribute) {
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
}
