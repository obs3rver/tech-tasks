package pl.techgarden.tasks.payu;

public class InvalidRequestArgumentException extends RuntimeException {

    InvalidRequestArgumentException(String message) {
        super(message);
    }

}
