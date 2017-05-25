package io.github.codejanovic.java.xml;

import java.util.Iterator;
import java.util.Optional;

public interface Xml {
    XmlElement root();

    Optional<XmlElement> findElement(String xpath);
    Iterator<XmlElement> findElements(String xpath);
    Optional<XmlAttribute> findAttribute(String xpath);
    Iterator<XmlAttribute> findAttributes(String xpath);

}
