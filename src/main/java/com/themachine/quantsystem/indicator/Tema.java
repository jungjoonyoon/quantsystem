package com.themachine.quantsystem.indicator;

import com.themachine.quantsystem.entity.TradeBar;

public class Tema implements Indicator {
	
	private Ema firstEma;
	private Ema secondEma;
	private Ema thirdEma;
	
	/**
	 * @param period
	 */
	public Tema(int period) {
		firstEma = new Ema(period);
		secondEma = new Ema(period);
		thirdEma = new Ema(period);
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
		return calculateTema(data);
	}

	/**
	 * @param data
	 * @return
	 */
	private Double calculateTema(Double data) {
		Double ema1 = firstEma.calculate(data);
		Double ema2 = secondEma.calculate(ema1);
		Double ema3 = thirdEma.calculate(ema2);
		
		return 3 * ema1 - 3 * ema2 + ema3;
	}
}
