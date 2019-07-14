package com.themachine.quantsystem.entity;

import java.util.Date;

public class Position {
	
	public enum PositionType {
		Sell, Buy
	}

	private Date entryTime;
	private Double entryPrice;
	private Date exitTime;
	private Double exitPrice;
	private Long amount;
	private PositionType type;
	
	/**
	 * @return the entryTime
	 */
	public Date getEntryTime() {
		return entryTime;
	}
	
	/**
	 * @param entryTime the entryTime to set
	 */
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * @return the entryPrice
	 */
	public Double getEntryPrice() {
		return entryPrice;
	}

	/**
	 * @param entryPrice the entryPrice to set
	 */
	public void setEntryPrice(Double entryPrice) {
		this.entryPrice = entryPrice;
	}

	/**
	 * @return the exitTime
	 */
	public Date getExitTime() {
		return exitTime;
	}

	/**
	 * @param exitTime the exitTime to set
	 */
	public void setExitTime(Date exitTime) {
		this.exitTime = exitTime;
	}

	/**
	 * @return the exitPrice
	 */
	public Double getExitPrice() {
		return exitPrice;
	}

	/**
	 * @param exitPrice the exitPrice to set
	 */
	public void setExitPrice(Double exitPrice) {
		this.exitPrice = exitPrice;
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
	 * @return the type
	 */
	public PositionType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(PositionType type) {
		this.type = type;
	}
}
