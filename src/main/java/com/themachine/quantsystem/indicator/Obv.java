package com.themachine.quantsystem.indicator;

import com.themachine.quantsystem.entity.TradeBar;

public class Obv implements Indicator {

	private Double prevClose = null;
	private Double prevObv = null;

	/* (non-Javadoc)
	 * @see com.themachine.quantsystem.indicator.Indicator#calculate(com.themachine.quantsystem.entity.TradeBar)
	 */
	@Override
	public Double calculate(TradeBar data) {
		return calculateObv(data.getClose(), data.getVolume());
	}

	/* (non-Javadoc)
	 * @see com.themachine.quantsystem.indicator.Indicator#calculate(java.lang.Double)
	 */
	@Override
	public Double calculate(Double data) {
		throw new IllegalArgumentException("Close and volume are required.");
	}

	/**
	 * @param close
	 * @param volume
	 * @return
	 */
	private Double calculateObv(Double close, Long volume) {
		if (prevClose == null) {
			prevClose = close;
			prevObv = Double.valueOf(volume);
			return prevObv;
		}
		
		if (prevClose < close) {
			prevObv = prevObv + volume;
		} else if (prevClose > close) {
			prevObv = prevObv - volume;
		}
		
		prevClose = close;
		return prevObv;
	}
}
