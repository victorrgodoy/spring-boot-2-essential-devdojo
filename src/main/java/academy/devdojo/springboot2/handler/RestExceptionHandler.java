package academy.devdojo.springboot2.handler;

import academy.devdojo.springboot2.exception.BadRequestException;
import academy.devdojo.springboot2.exception.BadRequestExceptionDetails;
import academy.devdojo.springboot2.exception.ValidationException;
import academy.devdojo.springboot2.exception.ValidationExceptionDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestDetail(
            BadRequestException badRequestException, HttpServletRequest httpServletRequest){

        return new ResponseEntity<>(
                BadRequestExceptionDetails
                        .builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Bad Request Exception, Check the documentation")
                        .message(badRequestException.getClass().getName())
                        .path(httpServletRequest.getRequestURI())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationExceptionDetails> handleValidation(
            ValidationException validationException, HttpServletRequest httpServletRequest) {

        return new ResponseEntity<>(
                ValidationExceptionDetails
                        .builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Validation Exception, Check the documentation")
                        .message(validationException.getClass().getName())
                        .path(httpServletRequest.getRequestURI())
                        .build(), HttpStatus.BAD_REQUEST);

    }

}
