package com.jgalante.balance.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Group {

	private String title;
	private Category category;
	private Transaction transaction;
	private boolean onlyCategory = false;
	private List<BigDecimal> values;
	private boolean showing = false;
	private Set<Group> subCategories;
	private boolean hasSubCategories = false;
	private BigDecimal totalParcialValue;
	private BigDecimal totalAbsParcialValue;
	private BigDecimal totalValue;

	public Group() {
		super();
	}

	public Group(Category category, Transaction transaction) {
		super();
		this.category = category;
		String name = "";
		if (transaction == null) {
			this.transaction = new Transaction();
		} else {
			this.transaction = transaction;
			addValue(this.getTransaction().getTransactionDate(),
					this.transaction.getValue(), 0, 11, category.getPositive());
		}
		if (this.category.getPerson() != null) {
			name = " - " + this.category.getPerson().getName();
		}
		this.title = category.getText() + name;
		//if (this.transaction.getId() == null) {
		if (this.category.getLevel().equals(1)) {
			this.onlyCategory = true;	
		}
	}

	public void addValue(Date date, BigDecimal value, int monthStart, int monthEnd, Boolean positive) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		Integer month = cal.get(Calendar.MONTH)- monthStart;

		if (values == null) {
			values = new LinkedList<BigDecimal>();
			for (int i = 0; i < monthEnd + 1 - monthStart; i++) {
				values.add(null);
			}
			totalParcialValue = BigDecimal.ZERO;
			totalAbsParcialValue = BigDecimal.ZERO;
		}
		BigDecimal monthValue = values.get(month);
		if (monthValue == null) {
			monthValue = BigDecimal.ZERO;
		} 
		if (positive) {
			values.set(month, monthValue.add(value));
			totalParcialValue = totalParcialValue.add(value);
		} else {
			values.set(month, monthValue.subtract(value));
			totalParcialValue = totalParcialValue.subtract(value);
		}
		totalAbsParcialValue = totalAbsParcialValue.add(value);
	}
	
	public void addSubCategories(Group subCategory) {
		if (subCategory != null){
			if (subCategories == null) {
				subCategories = new LinkedHashSet<Group>();
			}		
			
			subCategories.add(subCategory);
			hasSubCategories = true;
			this.showing = true;
			subCategory.setShowing(true);;
		}
	}
	
	public void invertShowing() {
		for (Group item : subCategories) {
			item.setShowing(!item.isShowing());			
		}
		showing = !showing;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isOnlyCategory() {
		return onlyCategory;
	}

	public List<BigDecimal> getValues() {
		return values;
	}

	public void setValues(List<BigDecimal> values) {
		this.values = values;
	}
	
	public BigDecimal getTotalParcialValue() {
		return totalParcialValue;
	}
	
	public BigDecimal getTotalAbsParcialValue() {
		return totalAbsParcialValue;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public boolean isShowing() {
		return showing;
	}

	public void setShowing(boolean showing) {
		this.showing = showing;
	}
	
	public Set<Group> getSubCategories() {
		return subCategories;
	}
	
	public boolean isHasSubCategories() {
		return hasSubCategories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category)) {
			return false;
		} 
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.getTransaction())) {
			return false;
		}
		
		return true;
	}

	
}
