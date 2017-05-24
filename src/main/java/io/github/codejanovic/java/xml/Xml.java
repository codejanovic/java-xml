package io.github.codejanovic.java.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public interface Xml {
    XmlElement root();

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
        public String toString() {
            return f("<? xml=fromCode> \n%s", root());
        }

        @Override
        public XmlElement root() {
            return new XmlElement.Dom4J(document.getRootElement());
        }
    }
}
