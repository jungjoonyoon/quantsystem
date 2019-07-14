package com.themachine.quantsystem.indicator;

import com.themachine.quantsystem.entity.TradeBar;

public class Zlt implements Indicator {

	private Tema firstTema;
	private Tema secondTema;
	
	/**
	 * @param period
	 */
	public Zlt(int period) {
		firstTema = new Tema(period);
		secondTema = new Tema(period);
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
		return calculateZlt(data);
	}

	/**
	 * @param data
	 * @return
	 */
	private Double calculateZlt(Double data) {
		Double tema1 = firstTema.calculate(data);
		Double tema2 = secondTema.calculate(tema1);
		
		return 2 * tema1 - tema2;
	}
}
