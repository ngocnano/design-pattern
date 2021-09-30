package com.ngoctm.builder;


import java.util.ArrayList;

// Some objects are simple and created in a single constructor call
// Other object require a lot of ceremony to create, many component
// Having object with 10 constructor arg is not productive
// Instead , opt for piecewise construction
// Builder provides an API for constructing object step by step
public class Demo {



    public static void main(String[] args) {
        String hello = "Hello";
        System.out.println("<p>" + hello + "</p>");

        String[] words = {"Hello", "hi"};
        System.out.println(
                "<li>" + words[0] + "</li>"
                + "<li>" + words[1] + "</li>"
        );

        //builder
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul>");
        for(String word : words){
            stringBuilder.append(String.format("<li>%s</li>", word));
        }
        stringBuilder.append("</ul>");
        ///
        HtmlElementBuilder htmlElementBuilder = new HtmlElementBuilder("ul");
        htmlElementBuilder.addChildElement("li", "Hello").addChildElement("li", "hm");
        htmlElementBuilder.addChildElement("li", "Hello1");
        System.out.println(htmlElementBuilder.toString());
    }
}

class HtmlElement {
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement(){

    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String toStringImpl(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s%s",name, newLine));
        if(text != null && !text.isEmpty()){
            stringBuilder.append(text).append(newLine);
        }
        for(HtmlElement htmlElement : elements){
            stringBuilder.append(htmlElement.toString());
        }
        stringBuilder.append(String.format("%s%s",name, newLine));
        return stringBuilder.toString();
    }

    public String toString(){
        return toStringImpl();
    }
}

class HtmlElementBuilder{
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlElementBuilder(String rootName){
        this.rootName = rootName;
        root.name = rootName;
    }

    // fluent interface
    public HtmlElementBuilder addChildElement(String childName, String childText){
        HtmlElement htmlElement = new HtmlElement(childName, childText);
        root.elements.add(htmlElement);
        return this;
    }

    public void clear(){
        root = new HtmlElement();
        root.name = rootName;
    }

    public String toString(){
        return root.toString();
    }
}

