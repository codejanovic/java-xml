package io.github.codejanovic.java.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public interface Xml {
    XmlElement root();

    XmlElement findElement(String xpath);
    Iterator<XmlElement> findElements(String xpath);
    XmlAttribute findAttribute(String xpath);
    Iterator<XmlAttribute> findAttributes(String xpath);

    final class Dom4J implements Xml {
        private final Document document;

        public Dom4J(final File file) {
            try {
                this.document = new SAXReader().read(file);
            } catch (DocumentException e) {
                throw new IllegalArgumentException("unable to load xml document." + e);
            }
        }

        public Dom4J(final InputStream stream) {
            try {
                this.document = new SAXReader().read(stream);
            } catch (DocumentException e) {
                throw new IllegalArgumentException(f("unable to load xml document: %s", stream) + e);
            }
        }

        @Override
        public XmlElement root() {
            return new XmlElement.Dom4J(document.getRootElement());
        }

        @Override
        public XmlElement findElement(String xpath) {
            return new XmlElement.Dom4J((Element) document.selectSingleNode(xpath));
        }

        @Override
        public Iterator<XmlElement> findElements(String xpath) {
            return new XmlElementIterator.Dom4j(document.selectNodes(xpath));
        }

        @Override
        public XmlAttribute findAttribute(String xpath) {
            return new XmlAttribute.Dom4j((Attribute) document.selectSingleNode(xpath));
        }

        @Override
        public Iterator<XmlAttribute> findAttributes(String xpath) {
            return new XmlAttributeIterator.Dom4J(document.selectNodes(xpath));
        }

        @Override
        public String toString() {
            return f("<? xml=fromCode> \n%s", root());
        }
    }
}
