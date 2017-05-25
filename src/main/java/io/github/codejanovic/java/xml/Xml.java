package io.github.codejanovic.java.xml;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public interface Xml {
    XmlElement root();

    Optional<XmlElement> findElement(String xpath);
    Iterator<XmlElement> findElements(String xpath);
    Optional<XmlAttribute> findAttribute(String xpath);
    Iterator<XmlAttribute> findAttributes(String xpath);

    final class Dom4J implements Xml {
        private final Document document;

        public Dom4J(final File file) throws DocumentException {
            this.document = new SAXReader().read(file);
        }

        public Dom4J(final InputStream stream) throws DocumentException {
            this.document = new SAXReader().read(stream);
    }

        @Override
        public XmlElement root() {
            return new XmlElement.Dom4J(document.getRootElement());
        }

        @Override
        public Optional<XmlElement> findElement(String xpath) {
            final Node result = document.selectSingleNode(xpath);
            return result == null? Optional.empty(): Optional.of(new XmlElement.Dom4J((Element) result));
        }

        @Override
        public Iterator<XmlElement> findElements(String xpath) {
            final List result = document.selectNodes(xpath);
            return new XmlElementIterator.Dom4j(result);
        }

        @Override
        public Optional<XmlAttribute> findAttribute(String xpath) {
            final Node result = document.selectSingleNode(xpath);
            return result == null? Optional.empty(): Optional.of(new XmlAttribute.Dom4j((Attribute) result));
        }

        @Override
        public Iterator<XmlAttribute> findAttributes(String xpath) {
            final List result = document.selectNodes(xpath);
            return new XmlAttributeIterator.Dom4J(result);
        }

        @Override
        public String toString() {
            return f("<? xml=fromCode> \n%s", root());
        }
    }
}
