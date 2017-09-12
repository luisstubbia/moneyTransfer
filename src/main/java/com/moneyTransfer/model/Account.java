package com.moneyTransfer.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author luis.stubbia
 *
 */
@Entity
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "name" }) })
public class Account extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
	private Set<Transaction> movements = new LinkedHashSet<Transaction>();

	@Transient
	private PricingSummary pricingSummary;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Transaction> getMovements() {
		return movements;
	}

	public void setMovements(Set<Transaction> movements) {
		this.movements = movements;
	}

	public PricingSummary getPricingSummary() {
		return PricingSummary.build(movements);
	}
}
