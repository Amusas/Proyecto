package Exceptions;

public class NegativeNumberException extends Exception {
    public NegativeNumberException(String s) {
        super (s);
    }

    public NegativeNumberException() {
        super ();
    }
}