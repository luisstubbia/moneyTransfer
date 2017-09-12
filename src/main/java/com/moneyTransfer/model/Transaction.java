package com.moneyTransfer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author luis.stubbia
 *
 */
@Entity
@Table(name = "transaction")
public class Transaction extends AbstractEntity implements Cloneable {

	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private Account account;

	@ManyToOne
	@JoinColumn(name = "account_to_id")
	@JsonIgnore
	private Account accountTo;

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	@Column(name = "type", nullable = false)
	private TransactionType type;

	@OneToOne
	@JoinColumn(name = "ref_id")
	@JsonIgnore
	private Transaction ref;

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
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	public Transaction getRef() {
		return ref;
	}

	public void setRef(Transaction ref) {
		this.ref = ref;
	}
	
	@Override
	public String toString(){
		StringBuilder st = new StringBuilder();
		st.append(type.name());
		st.append(": ");
		st.append(account.getName());
		st.append(" --> ");
		st.append(accountTo.getName());
		return st.toString();
	}
}