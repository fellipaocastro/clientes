package io.github.fellipecastro.clientes.rest;

import io.github.fellipecastro.clientes.rest.exception.ApiErrors;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiErrors handleValidationErrors(MethodArgumentNotValidException e) {
    BindingResult bindingResult = e.getBindingResult();
    List<String> messages = bindingResult
      .getAllErrors()
      .stream()
      .map(DefaultMessageSourceResolvable::getDefaultMessage)
      .collect(Collectors.toList());

    return new ApiErrors(messages);
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity handleResponseStatusException(
    ResponseStatusException e
  ) {
    String mensagemErro = e.getMessage();
    HttpStatus codigoStatus = e.getStatus();
    ApiErrors apiErrors = new ApiErrors(mensagemErro);
    return new ResponseEntity(apiErrors, codigoStatus);
  }
}
