package io.github.codejanovic.java.xml.factories;

public interface Factory2<R, P1, P2> {
    R provide(P1 param1, P2 param2);
}
