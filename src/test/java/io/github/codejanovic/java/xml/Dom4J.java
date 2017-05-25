package io.github.codejanovic.java.xml;

import org.xembly.Xembler;

import java.io.ByteArrayInputStream;

public class Dom4J extends XmlTestcases {
    @Override
    protected Xml provideXml(Xembler xembler) throws Exception {
        return new Xml.Dom4J(new ByteArrayInputStream(xembler.xml().getBytes()));
    }
}
