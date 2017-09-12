package com.moneyTransfer.util;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

/**
 * Exception Wrapper.
 * 
 * @author luis.stubbia
 *
 */
public class ServiceException extends NotFoundException {

	private static final long serialVersionUID = 6963357305129439717L;

	private List<BussinesError> errors;
	
	public ServiceException(BussinesError error) {
		this.addError(error);
	}

	public ServiceException(List<BussinesError> list) {
		this.errors = list;
	}

	public List<BussinesError> getErrors() {
		return errors;
	}

	public void setErrors(List<BussinesError> errors) {
		this.errors = errors;
	}

	public void addError(BussinesError error) {
		if (errors == null || errors.isEmpty()) {
			errors = new ArrayList<BussinesError>();
		}
		errors.add(error);
	}
}
