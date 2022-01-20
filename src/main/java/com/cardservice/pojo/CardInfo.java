package com.cardservice.pojo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardInfo {
	
	private Long id;
	private String name;
	private String creditCard;
	private BigDecimal balance;
	private BigDecimal limit;

	public Long getId() {
		return id;
	}

	public CardInfo setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public CardInfo setName(String name) {
		this.name = name;
		return this;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public CardInfo setCreditCard(String creditCard) {
		this.creditCard = creditCard;
		return this;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public CardInfo setBalance(BigDecimal balance) {
		this.balance = balance;
		return this;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public CardInfo setLimit(BigDecimal limit) {
		this.limit = limit;
		return this;
	}

}
