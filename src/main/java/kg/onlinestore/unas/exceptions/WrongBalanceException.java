package kg.onlinestore.unas.exceptions;

public class WrongBalanceException extends Exception {
    public static final String message = "There are not enough funds on the balance";

    public WrongBalanceException() {
        super(message);
    }
}
