package SolidDesignPrinciples.InterfaceSegregationPrinciple;


public class Document {

}

interface Machine {
    void print(Document d);

    void scan(Document d) throws Exception;

    void fax(Document d);
}

class MultiFunctionMachine implements Machine {
    @Override
    public void print(Document d) {
        // something
    }

    @Override
    public void scan(Document d) {
        // something
    }

    @Override
    public void fax(Document d) {
        // something
    }
}

class OldPrintMachine implements Machine {
    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) throws Exception {
        // We do not need to implement this!
        // this is bad!
        throw new Exception();
    }

    @Override
    public void fax(Document d) {
        // we do not need to implement this!
    }
}

/*
     To avoid the situation above, we should divide our interface!
 */
interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

interface Faxer {
    void fax(Document d);
}

// how can we combine them?
// oneway
class CopyMachine implements Scanner, Faxer {
    @Override
    public void scan(Document d) {

    }

    @Override
    public void fax(Document d) {

    }
}

// another way.
interface CopyDevice extends Scanner, Faxer {
}

class CopyMachine2 implements CopyDevice {
    @Override
    public void scan(Document d) {

    }

    @Override
    public void fax(Document d) {

    }
}

// way 3: decorator, we will cover this later on.
