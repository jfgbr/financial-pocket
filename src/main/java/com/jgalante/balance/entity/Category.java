package com.jgalante.balance.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.jgalante.jgcrud.entity.BaseEntity;

@Entity(name="CATEGORY")
public class Category extends BaseEntity {

	@Column(name = "text", nullable = false)
	private String text;

	@JoinColumn(name = "id_parent")
//	@ManyToOne(fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.LAZY)
	private Category parent;

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private Set<Category> subCategories;

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Transaction> transactions;
	
	@Column(name = "nr_order", nullable = false)
	private Integer order = 1;
	
	@Transient
	private Integer level = 1;
	
	public Category() {
		super();
	}
	
	public Category(String text) {
		super();
		this.text = text;
	}

	public Category(String text, Category parent) {
		super();
		this.text = text;
		this.parent = parent;
	}
	
	public Category(String text, Set<Category> subCategories) {
		super();
		this.text = text;
		this.subCategories = subCategories;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public Integer getLevel() {
		if (parent != null && level == 1) {
			level += parent.getLevel();
		}
		return level;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (parent != null) {
			sb.append(parent.toString());
			sb.append(" > ");
		}
		sb.append(this.getText());
		return sb.toString();
	}
}
