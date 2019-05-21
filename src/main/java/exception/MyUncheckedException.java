package exception;

import java.io.IOException;

public class MyUncheckedException extends RuntimeException {
    private String message;

    public MyUncheckedException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
