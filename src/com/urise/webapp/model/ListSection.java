package com.urise.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<String> strings;

    public ListSection() {
    }

    public ListSection(List<String> strings) {
        Objects.requireNonNull(strings, "strings must not be null");
        this.strings = strings;
    }

    public void addString(String newString) {
        strings.add(newString);
    }

    public List<String> getList() {
        return strings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return strings.equals(that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        return String.join("\n", strings);
    }
}