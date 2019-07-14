package com.themachine.quantsystem.indicator;

import com.themachine.quantsystem.entity.TradeBar;

public class Ema implements Indicator {

	private Double prevEma = null;
	private int period;
	
	/**
	 * @param period
	 */
	public Ema(int period) {
		this.period = period;
	}
	
	/* (non-Javadoc)
	 * @see com.themachine.quantsystem.indicator.Indicator#calculate(com.themachine.quantsystem.entity.TradeBar)
	 */
	@Override
	public Double calculate(TradeBar data) {
		return calculate(data.getClose());
	}

	/* (non-Javadoc)
	 * @see com.themachine.quantsystem.indicator.Indicator#calculate(java.lang.Double)
	 */
	@Override
	public Double calculate(Double data) {
		return calculateEma(data);
	}

	/**
	 * @param price
	 * @return
	 */
	private Double calculateEma(Double price) {
		if (prevEma == null) {
			prevEma = price;
			return price;
		}
		
		Double k = 2.0 / (period + 1.0);
		prevEma = (price * k) + (prevEma * (1.0 - k));
		
		return prevEma;
	}
}
