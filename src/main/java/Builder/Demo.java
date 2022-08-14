package Builder;

import java.util.ArrayList;
import java.util.Collections;

class HtmlElement {
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int identSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * identSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies((indent + 1) * identSize, " ")))
                    .append(text)
                    .append(newLine);
        }
        for (HtmlElement e : elements) {
            sb.append(e.toStringImpl(indent + 1));
        }
        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

class HtmlBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        this.root.name = rootName;
    }

    public void addChild(String childName, String childText) {
        HtmlElement child = new HtmlElement(childName, childText);
        root.elements.add(child);
    }

    public void clear() {
        this.root = new HtmlElement();
        this.root.name = rootName;
    }

    @Override
    public String toString() {
        return this.root.toString();
    }
}

public class Demo {


//    public static void main(String[] args) {
//        String hello = "hello";
//        System.out.println("<p>" + hello + "</p>");
//
//        String[] words = {"hello", "world"};
//
//        // piece wise builder
//        StringBuilder sb = new StringBuilder();
//        sb.append("<ul>\n");
//        for (String word : words) {
//            sb.append(String.format(" <li>%s</li>\n", word));
//        }
//        sb.append("</ul>");
//        System.out.println(sb);
//    }

    public static void main(String[] args) {
        HtmlBuilder hb = new HtmlBuilder("ul");
        hb.addChild("li", "hello");
        hb.addChild("li", "world");
        System.out.println(hb);

        StringBuilder sb = new StringBuilder();
        // this is called fluent interface
        sb.append("hello").append("world");
    }
}

