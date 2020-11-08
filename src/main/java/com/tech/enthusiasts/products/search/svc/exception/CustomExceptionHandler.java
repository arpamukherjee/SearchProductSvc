package com.tech.enthusiasts.products.search.svc.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tech.enthusiasts.products.search.svc.constant.CommonConstant;
import com.tech.enthusiasts.products.search.svc.vo.response.ErrorView;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorView> handleException(final HttpServletRequest request, final Exception exception){
		final ErrorView errorView = new ErrorView();
		log.info("Exception :: {} occured when executing request URL :: {} ", exception.getMessage(), request.getRequestURL());
		errorView.setErrorCode(CommonConstant.STATUS_FAILURE_CODE);
		errorView.setErrorType(CommonConstant.STATUS_FAILURE);
		errorView.setErrorDetail(exception.getMessage());
		return ResponseEntity.ok(errorView);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorView> handleInvalidRequestException(final HttpServletRequest request, final MethodArgumentNotValidException exception){
		final ErrorView errorView = new ErrorView();
		log.info("Exception :: {} occured when executing request URL :: {} ", exception.getMessage(), request.getRequestURL());
		final FieldError error = exception.getBindingResult().getFieldErrors().get(0);
		errorView.setErrorCode(CommonConstant.STATUS_FAILURE_CODE_INVALID_IP);
		errorView.setErrorType(CommonConstant.STATUS_FAILURE_INVALID_IP);
		errorView.setErrorDetail(error.getField() + CommonConstant.SPACE_CHAR + error.getDefaultMessage());
		return ResponseEntity.ok(errorView);
	}
}