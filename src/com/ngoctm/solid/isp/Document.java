package com.ngoctm.solid.isp;

public class Document {
    public String content;
}

interface Machine{
    void print(Document document);
    void fax(Document document) throws Exception;
    void scan(Document document);
}

class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document document) {

    }

    @Override
    public void fax(Document document) {

    }

    @Override
    public void scan(Document document) {

    }
}

// customer need machine old with 1 fun ??
class OldFashionedPrinter implements Machine{

    @Override
    public void print(Document document) {
        //
    }

    @Override
    public void fax(Document document) throws Exception {
        throw new Exception("Not implement");
    }

    @Override
    public void scan(Document document) {

    }
}


/// solution

interface Printer{
    void print(Document document);
}

interface Scan{
    void scan(Document document);
}

interface Fax{
    void fax(Document document);
}

// YAGNI = You Ain't Going to need it

class JustPrinter implements Printer{

    @Override
    public void print(Document document) {

    }
}