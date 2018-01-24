package pl.techgarden.tasks.geolocation;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequestMapping("/api/locations")
@RestController
@RequiredArgsConstructor
class GeolocationController {
    private final SaveGeolocationUseCase saveGeolocationUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void saveGeolocation(@RequestBody @Validated Geolocation.Dto dto) {
        saveGeolocationUseCase.saveGeolocation(dto);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(MethodArgumentNotValidException exception) {
        return ErrorResponse.of(
                exception.getBindingResult().getFieldErrors().stream()
                        .map(it -> String.format("%s %s", it.getField(), it.getDefaultMessage()))
                        .collect(toList())
        );
    }

    @Value(staticConstructor = "of")
    static class ErrorResponse {
        List<String> messages;
    }
}
