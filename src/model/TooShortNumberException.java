package model;

import java.io.IOException;

public class TooShortNumberException extends IOException {
    public String exceptionMessage() {
        return "The number is too short. Try again.";
    }
}
