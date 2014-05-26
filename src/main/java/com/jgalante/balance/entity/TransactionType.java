package com.jgalante.balance.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.jgalante.jgcrud.entity.BaseEntity;

@Entity(name="TP_TRANSACTION")
public class TransactionType extends BaseEntity {

	public static final TransactionType POSITIVE = new TransactionType("POSITIVE", true);
	public static final TransactionType NEGATIVE = new TransactionType("NEGATIVE", false);

	@Column(name = "text", nullable = false)
	private String text;

	@Column(name = "positive", nullable = false)
	private Boolean positive = true;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transactionType")
	private Set<Transaction> transactions;
	
	public TransactionType() {
		super();
	}
	
	public TransactionType(String text, Boolean positive) {
		super();
		this.text = text;
		this.positive = positive;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getPositive() {
		return positive;
	}

	public void setPositive(Boolean positive) {
		this.positive = positive;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

}
