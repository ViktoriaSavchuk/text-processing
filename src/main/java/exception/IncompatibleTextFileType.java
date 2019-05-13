package exception;

public class IncompatibleTextFileType extends RuntimeException {


    public IncompatibleTextFileType() {
    }

    public IncompatibleTextFileType(String message) {
        super(message);
    }

    public IncompatibleTextFileType(String message, Throwable cause) {
        super(message, cause);
    }
}
