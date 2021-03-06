package io.github.codejanovic.java.xml;

import java.util.Set;
import java.util.stream.Collectors;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public interface XmlElement extends XmlAttribute {
    StreamIterable<XmlAttribute> attributes();
    StreamIterable<XmlElement> elements();
    StreamIterable<XmlElement> recursive();

    final class Features {
        private Features() {
        }

        public static XmlElement ignoreWhitespaces(final XmlElement decorate) {
            return new IgnoreWhitespaces(decorate);
        }
    }

    abstract class Abstract implements XmlElement {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || !(o instanceof XmlElement)) return false;

            XmlElement that = (XmlElement) o;

            if (name() != null ? !name().equals(that.name()) : that.name() != null) return false;
            if (value() != null ? !value().equals(that.value()) : that.value() != null) return false;
            return compareAttributesIgnoreOrder(that);
        }

        private boolean compareAttributesIgnoreOrder(final XmlElement that) {
            final Set<XmlAttribute> thisAttributes = attributes().stream().collect(Collectors.toSet());
            final Set<XmlAttribute> thatAttributes = that.attributes().stream().collect(Collectors.toSet());

            return thisAttributes.equals(thatAttributes);
        }

        @Override
        public int hashCode() {
            int result = name() != null ? name().hashCode() : 0;
            result = 31 * result + (value() != null ? value().hashCode() : 0);
            result = 31 * result + (attributes() != null? attributes().stream().sorted().collect(Collectors.toList()).hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return f("element [name: %s | value: %s], attributes [%s]", name(), value(), attributes().stream().map(a -> f("attribute [name: %s | value: %s", a.name(), a.value())).collect(Collectors.joining(", ")));
        }
    }

    final class IgnoreWhitespaces extends Abstract {
        private final XmlElement decorated;

        public IgnoreWhitespaces(XmlElement decorated) {
            this.decorated = decorated;
        }

        @Override
        public String name() {
            return decorated.name();
        }

        @Override
        public String value() {
            return decorated.value().trim();
        }

        @Override
        public StreamIterable<XmlAttribute> attributes() {
            return decorated.attributes();
        }

        @Override
        public StreamIterable<XmlElement> elements() {
            return decorated.elements();
        }

        @Override
        public StreamIterable<XmlElement> recursive() {
            return decorated.recursive();
        }
    }
}
