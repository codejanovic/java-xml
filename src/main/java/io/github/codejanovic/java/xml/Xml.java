package io.github.codejanovic.java.xml;

import java.util.Optional;

public interface Xml {
    XmlElement root();

    Optional<XmlElement> findElement(String xpath);
    StreamIterable<XmlElement> findElements(String xpath);
    Optional<XmlAttribute> findAttribute(String xpath);
    StreamIterable<XmlAttribute> findAttributes(String xpath);

}
