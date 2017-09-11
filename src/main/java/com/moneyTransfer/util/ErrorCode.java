package com.moneyTransfer.util;

/**
 * 
 * @author luis.stubbia
 *
 */
public enum ErrorCode {

	USER_NOT_FOUND("1001", "User not found.", 404), 
	ACCOUNT_NOT_FOUND("1002", "Account not found.", 404), 
	TX_NOT_FOUND("1003", "Transaction not found.", 404), 
	
	USER_MISSING_USERNAME("2001","Missing user name.", 400),
	USER_MISSING_LASTNAME("2002","Missing user last name.", 400),
	USER_MISSING_FIRSTNAME("2003","Missing user first name.", 400),
	
	SERVICE_ERROR("3002", "Internal server error.", 500);
	
	private String code;
	private String description;
	private int status;

	private ErrorCode(String code, String description, int status) {
		this.code = code;
		this.description = description;
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public int getStatus() {
		return status;
	}

	/**
	 * Obtain and return the ErrorCode enum matching the given code.
	 *
	 * @param code
	 * @return ErrorCode or null;
	 */
	public static ErrorCode lookupByCode(String code) {
		if (code != null && !code.isEmpty()) {
			for (ErrorCode typeEnum : values()) {
				if (typeEnum.getCode().equalsIgnoreCase(code)) {
					return typeEnum;
				}
			}
		}
		return null;
	}
}
