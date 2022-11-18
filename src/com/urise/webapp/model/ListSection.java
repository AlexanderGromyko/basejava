package com.urise.webapp.model;

import java.util.List;

public class ListSection extends AbstractSection{
    private final List<String> strings;

    public ListSection(List strings) {
        this.strings = strings;
    }

    public void addString(String newString) {
        strings.add(newString);
    }

    public List getStrings() {
        return strings;
    }

    @Override
    public void print() {
        strings.stream().forEach((v) -> System.out.println(v));
    }
}