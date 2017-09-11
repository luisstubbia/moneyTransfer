package com.moneyTransfer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author luis.stubbia
 *
 */
@Entity
@Table(name = "transaction")
public class Transaction extends AbstractEntity {
	
	@ManyToOne
    @JoinColumn(name = "account_id")
	private Account account;
	
	@ManyToOne
    @JoinColumn(name = "account_to_id")
	private Account accountTo;
	
	@Column(name="amount", nullable = false)
	private BigDecimal amount;
	
	@Column(name="type", nullable = false)
	private TransactionType type;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}
}