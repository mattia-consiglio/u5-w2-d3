package mattiaconsiglio.u5w2d3.exceptions;

import lombok.extern.slf4j.Slf4j;
import mattiaconsiglio.u5w2d3.payloads.ErrorPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayload handleRecordNotFoundException(RecordNotFoundException e) {
        return new ErrorPayload(e.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleBadRequestException(BadRequestException e) {
        return new ErrorPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ErrorPayload("The server did not understand the request. Check the correct format of the JSON passed, their keys and values.", LocalDateTime.now());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorPayload handleRuntimeException(RuntimeException e) {
        log.error("An error occurred", e);
        return new ErrorPayload("The server encountered an error. The error is reported to the developer.", LocalDateTime.now());
    }
}
