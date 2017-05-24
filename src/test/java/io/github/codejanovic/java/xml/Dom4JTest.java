package io.github.codejanovic.java.xml;

import org.junit.Test;
import org.xembly.Directives;
import org.xembly.Xembler;

import java.io.ByteArrayInputStream;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;

public class Dom4JTest {

    @Test
    public void testThatItWorks() throws Exception {
        System.out.println(f("initial xml: \n %s", testXml().xml()));
        final Xml xml = new Xml.Dom4J(new ByteArrayInputStream(testXml().xml().getBytes()));
        System.out.println(f("resulting xml: \n %s", xml));

    }

    /**
     * <root of="java-xml" version="initial">
     *     <lets>
     *         <test>
     *             <that it="works">
     *                  all childs are complete
     *             </that>
     *         </test>
     *     </lets>
     * </root>
     *
     */
    private Xembler testXml(){
        return new Xembler(
                new Directives()
                        .add("root")
                        .attr("of", "java-xml")
                        .attr("version", "initial")
                        .add("lets")
                        .add("test")
                        .add("that")
                        .attr("it", "works")
                        .set("all childs are complete")
        );
    }
}