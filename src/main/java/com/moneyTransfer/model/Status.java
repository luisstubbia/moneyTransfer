package com.moneyTransfer.model;

/**
 * 
 * @author luis.stubbia
 *
 */
public enum Status {
	
	ACTIVE("ACTIVE"),
	INACTIVE("INCATIVE"),
	DELETED("DELETED");
	
	private String name;

	Status(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
