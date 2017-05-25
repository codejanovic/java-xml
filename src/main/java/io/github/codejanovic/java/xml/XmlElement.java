package io.github.codejanovic.java.xml;

public interface XmlElement extends XmlAttribute {
    StreamIterable<XmlAttribute> attributes();
    StreamIterable<XmlElement> elements();
    StreamIterable<XmlElement> recursive();
}
