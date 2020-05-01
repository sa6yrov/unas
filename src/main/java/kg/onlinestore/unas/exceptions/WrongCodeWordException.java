package kg.onlinestore.unas.exceptions;

public class WrongCodeWordException extends Exception {
    public static final String message = "Wrong code word";

    public WrongCodeWordException(){
        super(message);
    }
}
