package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection{
    private List<String> strings;

    public ListSection() {
        this.strings = new ArrayList();
    }

    public void addString(String newString) {
        strings.add(newString);
    }

    public List getStrings() {
        return new ArrayList<>(strings);
    }

    @Override
    public void print() {
        strings.stream().forEach((v) -> System.out.println(v));
    }
}