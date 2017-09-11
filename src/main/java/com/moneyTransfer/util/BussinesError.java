package com.moneyTransfer.util;

import java.util.List;

/**
 * Business error mapper.
 * 
 * @author luis.stubbia
 *
 */
public class BussinesError {

	private final int status;
	private final String code;
	private final String description;
	private final String type;
	private final List<String> details;

	/**
	 * Full constructor.
	 * 
	 * @param error
	 * @param details
	 */
	public BussinesError(ErrorCode error, List<String> details) {
		this.status = error.getStatus();
		this.type = error.name();
		this.code = error.getCode();
		this.description = error.getDescription();
		this.details = details;
	}

	public BussinesError(ErrorCode error) {
		this.status = error.getStatus();
		this.type = error.name();
		this.code = error.getCode();
		this.description = error.getDescription();
		this.details = null;
	}

	public BussinesError(String description, List<String> details) {
		this.status = ErrorCode.SERVICE_ERROR.getStatus();
		this.type = ErrorCode.SERVICE_ERROR.name();
		this.code = ErrorCode.SERVICE_ERROR.getCode();
		this.description = description;
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public List<String> getDetails() {
		return details;
	}

	public int getStatus() {
		return status;
	}
}
