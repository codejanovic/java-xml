package io.github.codejanovic.java.xml.dom4j;

import io.github.codejanovic.java.xml.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public final class Dom4JXml implements Xml {
    private final Document document;

    public Dom4JXml(final File file) throws DocumentException {
        this.document = new SAXReader().read(file);
    }

    public Dom4JXml(final InputStream stream) throws DocumentException {
        this.document = new SAXReader().read(stream);
}

    @Override
    public XmlElement root() {
        return new Dom4JXmlElement(document.getRootElement());
    }

    @Override
    public Optional<XmlElement> findElement(String xpath) {
        final Node result = document.selectSingleNode(xpath);
        return result == null? Optional.empty(): Optional.of(new Dom4JXmlElement((Element) result));
    }

    @Override
    public Iterator<XmlElement> findElements(String xpath) {
        final List result = document.selectNodes(xpath);
        return new Dom4jXmlElementIterator(result);
    }

    @Override
    public Optional<XmlAttribute> findAttribute(String xpath) {
        final Node result = document.selectSingleNode(xpath);
        return result == null? Optional.empty(): Optional.of(new Dom4jXmlAttribute((Attribute) result));
    }

    @Override
    public Iterator<XmlAttribute> findAttributes(String xpath) {
        final List result = document.selectNodes(xpath);
        return new Dom4JXmlAttributeIterator(result);
    }

    @Override
    public String toString() {
        return f("<? xml=fromCode> \n%s", root());
    }
}
