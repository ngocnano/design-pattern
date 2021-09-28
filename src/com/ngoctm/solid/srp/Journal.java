package com.ngoctm.solid.srp;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text){
        this.entries.add(" " + (++count) + " : " + text);
    }

    public void removeEntry(int index){
        this.entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), this.entries);
    }

    // persistent vi phạm srp
    public void save(String filename) throws FileNotFoundException {
        try(PrintStream out = new PrintStream(filename)){
            out.println();
        }
    }

    public void load(String filename){}
    public void loadUrl(String filename){}
}

class Demo{
    public static void main(String[] args) {
        Journal journal = new Journal();
        journal.addEntry("I cried today");
        journal.addEntry("I ate bug");
        System.out.println(journal);
    }
}
