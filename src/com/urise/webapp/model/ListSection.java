package com.urise.webapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<String> strings;

    public ListSection(List<String> strings) {
        this.strings = strings;
    }

    public void addString(String newString) {
        strings.add(newString);
    }

    public List<String> getStrings() {
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
        strings.stream().forEach((v) -> str.append(v+"\n"));
        return str.toString();
    }
}