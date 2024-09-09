package com.admin.exceptions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.ConstraintViolationException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	@ExceptionHandler(UserNameNotFoundException.class)
	public ResponseEntity<String> exceptionHandler(UserNameNotFoundException e) {
		String s = e.getMessage();
		ResponseEntity<String> re = new ResponseEntity<String>(s, HttpStatus.NOT_FOUND);
		return re;
	}

	@ExceptionHandler(UserNameAlreadyExistsException.class)
	public ResponseEntity<String> exceptionHandler(UserNameAlreadyExistsException e) {
		String s = e.getMessage();
		ResponseEntity<String> re = new ResponseEntity<String>(s, HttpStatus.NOT_FOUND);
		return re;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMyException(MethodArgumentNotValidException ex) {
		Map<String, String> m = new LinkedHashMap<>();
		List<ObjectError> oerrors = ((Errors) ex).getAllErrors();
		oerrors.forEach(obj -> {
			FieldError ferror = (FieldError) obj;
			String fname = ferror.getField();
			String message = ferror.getDefaultMessage();
			m.put(fname, message);
		});
		ResponseEntity<Map<String, String>> re = new ResponseEntity<Map<String, String>>(m, HttpStatus.BAD_REQUEST);
		return re;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleException1(ConstraintViolationException e) {
		String s = e.getMessage();
		ResponseEntity<String> re = new ResponseEntity<String>(s, HttpStatus.BAD_REQUEST);
		return re;
	}
	 @ExceptionHandler(AdminServiceException.class)
	    public ResponseEntity<String> handleAdminServiceException(AdminServiceException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
}
