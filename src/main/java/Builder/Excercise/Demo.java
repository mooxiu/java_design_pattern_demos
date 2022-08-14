package Builder.Excercise;

/*
 Expected Output:

 public class Person
{
    public String name;
    public int age;
}
 */


import java.util.ArrayList;

class CodeBuilder {
    static class Field {
        String name;
        String type;

        public Field(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }
    protected String className;
    protected ArrayList<Field> fields = new ArrayList<>();
    public CodeBuilder(String className) {
        this.className = className;
    }

    public CodeBuilder addField(String name, String type) {
        this.fields.add(new Field(name, type));
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("public class %s\n", this.className));
        sb.append("{\n");
        for (Field f : this.fields) {
            sb.append(String.format("  public %s %s;\n", f.type, f.name));
        }
        sb.append("}");
        return sb.toString();
    }
}

public class Demo {
    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
        System.out.println(cb);
    }
}

