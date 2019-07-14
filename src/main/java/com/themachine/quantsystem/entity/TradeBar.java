package com.themachine.quantsystem.entity;

import java.util.Date;

public class TradeBar {
	
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Long volume;
	private Date time;
	private Double gapOpen;
	private Double gapHigh;
	private Double gapLow;
	private Double gapClose;
	private Double gap;
	
	/**
	 * @return the open
	 */
	public Double getOpen() {
		return open;
	}
	
	/**
	 * @param open the open to set
	 */
	public void setOpen(Double open) {
		this.open = open;
	}

	/**
	 * @return the high
	 */
	public Double getHigh() {
		return high;
	}

	/**
	 * @param high the high to set
	 */
	public void setHigh(Double high) {
		this.high = high;
	}

	/**
	 * @return the low
	 */
	public Double getLow() {
		return low;
	}

	/**
	 * @param low the low to set
	 */
	public void setLow(Double low) {
		this.low = low;
	}

	/**
	 * @return the close
	 */
	public Double getClose() {
		return close;
	}

	/**
	 * @param close the close to set
	 */
	public void setClose(Double close) {
		this.close = close;
	}

	/**
	 * @return the volume
	 */
	public Long getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(Long volume) {
		this.volume = volume;
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

	/**
	 * @return the gapOpen
	 */
	public Double getGapOpen() {
		return gapOpen;
	}

	/**
	 * @param gapOpen the gapOpen to set
	 */
	public void setGapOpen(Double gapOpen) {
		this.gapOpen = gapOpen;
	}

	/**
	 * @return the gapHigh
	 */
	public Double getGapHigh() {
		return gapHigh;
	}

	/**
	 * @param gapHigh the gapHigh to set
	 */
	public void setGapHigh(Double gapHigh) {
		this.gapHigh = gapHigh;
	}

	/**
	 * @return the gapLow
	 */
	public Double getGapLow() {
		return gapLow;
	}

	/**
	 * @param gapLow the gapLow to set
	 */
	public void setGapLow(Double gapLow) {
		this.gapLow = gapLow;
	}

	/**
	 * @return the gapClose
	 */
	public Double getGapClose() {
		return gapClose;
	}

	/**
	 * @param gapClose the gapClose to set
	 */
	public void setGapClose(Double gapClose) {
		this.gapClose = gapClose;
	}

	/**
	 * @return the gap
	 */
	public Double getGap() {
		return gap;
	}

	/**
	 * @param gap the gap to set
	 */
	public void setGap(Double gap) {
		this.gap = gap;
	}
	
}
