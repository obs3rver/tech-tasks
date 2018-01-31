package pl.techgarden.tasks.payu;

class InvalidRequestArgumentException extends RuntimeException {

    InvalidRequestArgumentException(String message) {
        super(message);
    }

}
