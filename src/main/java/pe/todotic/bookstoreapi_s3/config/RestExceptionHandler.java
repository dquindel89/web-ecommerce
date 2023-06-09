package pe.todotic.bookstoreapi_s3.config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.todotic.bookstoreapi_s3.exception.MediaFileNotFoundException;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private MessageSource messageSource; //esto es una fuente de mensajes (esto lo ofrece el framework)

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handlerValidationError(MethodArgumentNotValidException manve){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Unprocessable Entity");
        problemDetail.setType(URI.create("http://api.sbashop.com/errors/unprocessable-entity"));
        problemDetail.setDetail("the entity can´t processed because it has errors");


        List<FieldError> fieldErrors = manve.getFieldErrors();
        List<String> errors = new ArrayList<>();

        for(FieldError fe : fieldErrors){
            String message = messageSource.getMessage(fe, Locale.getDefault());
            errors.add(message);
        }

        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class/*, MediaFileNotFoundException.class*/})
    void handleEntityNotFoundException(){

    }
}
