package com.learning.microservice.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

	 //@ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(Exception.class)
	 public final ResponseEntity<Object> defaultExceptionHandler(Exception e, WebRequest request) {
		 ApiErrorResponse apiErrorResponse =new ApiErrorResponse.ApiErrorResponseBuilder()
			        .withStatus(HttpStatus.BAD_REQUEST)
			        .withError_code(HttpStatus.BAD_REQUEST.name())
			        .withDetail("Please check the URI of this request.")
			        .withMessage(e.getMessage()).build();
			        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(BookNotFoundException.class)
	 public final ResponseEntity<Object> BookNotFoundException(BookNotFoundException e, WebRequest request) {
		 ApiErrorResponse apiErrorResponse =new ApiErrorResponse.ApiErrorResponseBuilder()
			        .withStatus(HttpStatus.NOT_FOUND)
			        .withError_code(HttpStatus.NOT_FOUND.name())
			        .withDetail("Book Id Not Found in DataBase. Please try with different book id "+request.getDescription(false))
			        .withMessage(e.getMessage()).build();
			        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(DataIntegrityViolationException.class)
	 public final ResponseEntity<Object> saveViolationException(DataIntegrityViolationException e, WebRequest request) {
		 ApiErrorResponse apiErrorResponse =new ApiErrorResponse.ApiErrorResponseBuilder()
			        .withStatus(HttpStatus.BAD_REQUEST)
			        .withError_code(HttpStatus.BAD_REQUEST.name())
			        .withDetail("Book already exist with Book id. "+request.getDescription(false))
			        .withMessage(e.getMessage()).build();
			        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	 }
	
	 @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
				MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		 ApiErrorResponse apiErrorResponse =new ApiErrorResponse.ApiErrorResponseBuilder()
			        .withStatus(HttpStatus.BAD_REQUEST)
			        .withError_code(HttpStatus.BAD_REQUEST.name())
			        .withDetail(ex.getBindingResult().toString())  // Here you have use Binding Resust to loop to take different messages.
			        .withMessage("Validation Error. "+request.getDescription(false)).build();
			        return new ResponseEntity<>(apiErrorResponse, HttpStatus.BAD_REQUEST);
	}
}
