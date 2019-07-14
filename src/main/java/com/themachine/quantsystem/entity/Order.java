package com.themachine.quantsystem.entity;

import java.util.Date;

public class Order {
	
	public enum OrderType {
		Sell, Buy
	}
	
	private OrderType type;
	private Long amount;
	private Date time;
	
	/**
	 * @return the type
	 */
	public OrderType getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(OrderType type) {
		this.type = type;
	}

	/**
	 * @return the amount
	 */
	public Long getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}
}
