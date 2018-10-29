package model;

import java.io.IOException;

public class TooLongNumberException extends IOException {
    public String exceptionMessage() {
        return "The number is too long. Try again.";
    }
}
