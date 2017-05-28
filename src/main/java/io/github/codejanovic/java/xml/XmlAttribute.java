package io.github.codejanovic.java.xml;


public interface XmlAttribute {
    String name();
    String value();

    abstract class Abstract implements XmlAttribute {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || !(o instanceof XmlAttribute)) return false;

            XmlAttribute that = (XmlAttribute) o;

            if (name() != null ? !name().equals(that.name()) : that.name() != null) return false;
            return value() != null ? value().equals(that.value()) : that.value() == null;
        }

        @Override
        public int hashCode() {
            int result = name() != null ? name().hashCode() : 0;
            result = 31 * result + (value() != null ? value().hashCode() : 0);
            return result;
        }
    }

}
