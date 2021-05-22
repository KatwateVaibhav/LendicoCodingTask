package com.lendico.plangenerator.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lendico.plangenerator.exception.PlanGeneratorException;
import com.lendico.plangenerator.model.PlanError;


@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(PlanGeneratorException.class)
	public ResponseEntity<PlanError> mapException(PlanGeneratorException ex) {
		PlanError error = new PlanError(ex.getErrorCode(), ex.getErrorMsg());
		return new ResponseEntity<PlanError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}