package io.github.codejanovic.java.xml;

import io.github.codejanovic.java.xml.dom4j.Dom4JXml;
import org.xembly.Xembler;

import java.io.ByteArrayInputStream;

public class Dom4J extends XmlTestcases {
    @Override
    protected Xml provideXml(Xembler xembler) throws Exception {
        return new Dom4JXml(new ByteArrayInputStream(xembler.xml().getBytes()));
    }
}
