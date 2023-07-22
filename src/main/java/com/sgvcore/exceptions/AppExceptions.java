package com.sgvcore.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sgvcore.Model.ResponseAPI;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppExceptions {

    // string to snake case string
    public String camelToSnake(String str)
    {
        // Empty String
        String result = "";
        // Append first character(in lower case)
        // to result string
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);
        // Traverse the string from
        // ist index to last index
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result
                        = result
                        + Character.toLowerCase(ch);
            }
            // If the character is lower case then
            // add such character into result string
            else {
                result = result + ch;
            }
        }
        // return the result
        return result;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseAPI handleValidationExceptions(HttpServletRequest request,
                                                  MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            String fieldSnakeCase = camelToSnake(fieldName);
            errors.put(fieldSnakeCase, errorMessage);
        });
        return new ResponseAPI(false, "422", "Erro de Validação!", errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseAPI handleInvalidFormat(HttpServletRequest request,
                                                  InvalidFormatException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("valor_invalido", ex.getValue().toString());
        return new ResponseAPI(false, "400", "Inseriu um tipo de dado invalido!", error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ResponseAPI handleNullPointer(HttpServletRequest request,
                                           NullPointerException ex) {
        return new ResponseAPI(false, "400", "Um dos campos obrigatorios está nulo!", null);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ContentAlreadyExists.class)
    public Map<String, String> handleConflits(ContentAlreadyExists e){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String, String> handleUserNotFound(UsernameNotFoundException e){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ModelNotFound.class)
    public Map<String, String> handleModelNotFound(ModelNotFound e){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return errors;
    }

}
