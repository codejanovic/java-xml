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

    final class Fake extends Abstract {
        private final String name;
        private final String value;

        public Fake(final String name, final String value) {
            this.name = name;
            this.value = value;
        }

        public Fake() {
            this("", "");
        }

        public Fake withName(final String name) {
            return new Fake(name, value);
        }

        public Fake withValue(final String value) {
            return new Fake(name, value);
        }

        @Override
        public String name() {
            return name;
        }

        @Override
        public String value() {
            return value;
        }
    }
}
