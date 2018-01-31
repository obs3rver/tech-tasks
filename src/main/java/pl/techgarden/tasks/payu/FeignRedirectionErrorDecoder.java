package pl.techgarden.tasks.payu;

import feign.Response;
import feign.codec.ErrorDecoder;

import static feign.FeignException.errorStatus;

class FeignRedirectionErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 300 && response.status() <= 399) {
            return new FeignRedirectionException(response);
        }

        return errorStatus(methodKey, response);
    }

    static class FeignRedirectionException extends RuntimeException {
        private final Response response;

        FeignRedirectionException(Response response) {
            super(response.reason());
            this.response = response;
        }

        Response response() {
            return response;
        }
    }
}
