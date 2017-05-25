package io.github.codejanovic.java.xml;

import com.sun.xml.internal.bind.annotation.XmlIsSet;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;
import org.xembly.Directives;
import org.xembly.ImpossibleModificationException;
import org.xembly.Xembler;

import static io.github.codejanovic.java.shortcuts.Shortcuts.f;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public abstract class XmlTestcases {

    private Xml xml;

    protected abstract Xml provideXml(Xembler xembler) throws Exception;

    @Before
    public void before() throws Exception {
        this.xml = provideXml(testXml());
    }

    @Test
    public void testThatItWorks() throws Exception {
        System.out.println(f("initial xml: \n %s", testXml().xml()));
        System.out.println(f("resulting xml: \n %s", xml));
    }

    @Test
    public void testFindElement() {
        assertThat(xml.findElement("//root/lets/test/that"))
                .isPresent()
                .matches(e -> e.get().name().equalsIgnoreCase("that"))
                .matches(e -> e.get().value().equalsIgnoreCase("all childs are complete"));
    }

    @Test
    public void testFindNotExistingElement() {
        assertThat(xml.findElement("//root/lets/test/that/doesntExist"))
                .isNotPresent();
    }

    @Test
    public void testFindElements() {
        assertThat(xml.findElements("//root/lets/test/that"))
                .isNotEmpty()
                .hasSize(2)
                .extracting(XmlElement::name, XmlElement::value)
                .contains(tuple("that", "all childs are complete"), tuple("that", "it works"));
    }

    @Test
    public void testFindNotExistingElements() {
        assertThat(xml.findElements("//root/lets/test/that/doesntExist"))
                .isEmpty();
    }

    @Test
    public void testFindAttribute() {
        assertThat(xml.findAttribute("//root/lets/test/that/@it"))
                .isPresent()
                .matches(e -> e.get().name().equalsIgnoreCase("it"))
                .matches(e -> e.get().value().equalsIgnoreCase("works"));
    }

    @Test
    public void testFindNotExistingAttribute() {
        assertThat(xml.findElement("//root/lets/test/that/@doesntExist"))
                .isNotPresent();
    }

    @Test
    public void testFindAttributes() {
        assertThat(xml.findAttributes("//root/@*"))
                .isNotEmpty()
                .hasSize(2)
                .extracting(XmlAttribute::name, XmlAttribute::value)
                .contains(tuple("of", "java-xml"), tuple("version", "initial"));
    }

    @Test
    public void testFindNotExistingAttributes() {
        assertThat(xml.findElement("//root/lets/test/that/@doesntExist"))
                .isNotPresent();
    }

    /**
     * <root of="java-xml" version="initial">
     *     <lets>
     *         <test>
     *             <that it="works">
     *                  all childs are complete
     *             </that>
     *             <that it="works as well">
     *                 it works
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
                        .up()
                        .add("that")
                        .set("it works")
        );
    }
}