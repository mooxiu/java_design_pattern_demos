package SolidDesignPrinciples.SingleResponsibilityPrinciple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
    Responsibility for Journal class is only define the struct
 */
public class Journal {
    private List<String> entries = new ArrayList<>();
    private static int count;

    public void addEntry(String text) {
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    /*
    This is bad design, good design is to separate persistence into a single class
     */
//    public void save(String filename) throws FileNotFoundException {
//        try(PrintStream out = new PrintStream(filename)) {
//            out.println(toString());
//        }
//    }
//
//    public void load(String filename){}
//    public void load(URL url){}

}

/*
    the responsibility of Persistence class is to do the persistent things.
 */
class Persistence {
    public void saveJournal(Journal journal, String filename, boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(filename).exists()) {
            /*
                This is a try-with-resources statement, which is something like defer close() in golang.
             */
            try (PrintStream out = new PrintStream(filename)) {
                out.println(journal.toString());
            }
        }
    }

    public Journal load(String filename) {return new Journal();}
    public Journal load(URL url) {return new Journal();}
}

class demo {
    public static void main(String[] args) throws IOException {
        var j = new Journal();
        j.addEntry("I cried today");
        j.addEntry("I ate a bug");
        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "/Users/muyao/j.txt";
        p.saveJournal(j, filename, true);

        Runtime.getRuntime().exec(new String[]{"code", filename});
    }
}