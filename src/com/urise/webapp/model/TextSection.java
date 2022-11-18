package com.urise.webapp.model;

public class TextSection extends AbstractSection{
    private String content;

    public TextSection(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void print() {
        System.out.println(content);
    }
}