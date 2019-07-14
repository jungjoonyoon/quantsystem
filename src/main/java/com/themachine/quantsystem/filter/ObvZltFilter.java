package com.themachine.quantsystem.filter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.themachine.quantsystem.entity.TradeBar;
import com.themachine.quantsystem.indicator.Obv;
import com.themachine.quantsystem.indicator.Zlt;

@Component
public class ObvZltFilter implements Filter {
	
	private Double firstMa;
	private Double secondMa;
	private int firstPeriod;
	private int secondPeriod;
	
	private Obv obv;
	private Zlt firstZlt;
	private Zlt secondZlt;

	/**
	 * @param firstPeriod the firstPeriod to set
	 */
	public void setFirstPeriod(int firstPeriod) {
		this.firstPeriod = firstPeriod;
	}

	/**
	 * @param secondPeriod the secondPeriod to set
	 */
	public void setSecondPeriod(int secondPeriod) {
		this.secondPeriod = secondPeriod;
	}

	/* (non-Javadoc)
	 * @see com.themachine.quantsystem.filter.Filter#prepare(java.util.List)
	 */
	@Override
	public void prepare(List<TradeBar> history) {
		
		obv = new Obv();
		firstZlt = new Zlt(firstPeriod);
		secondZlt = new Zlt(secondPeriod);
		
		for (TradeBar tradeBar : history) {
			Double obvVal = obv.calculate(tradeBar);
			firstMa = firstZlt.calculate(obvVal);
			secondMa = secondZlt.calculate(obvVal);
		}
	}

	/* (non-Javadoc)
	 * @see com.themachine.quantsystem.filter.Filter#onData(com.themachine.quantsystem.entity.TradeBar)
	 */
	@Override
	public void onData(TradeBar data) {
		Double obvVal = obv.calculate(data);
		firstMa = firstZlt.calculate(obvVal);
		secondMa = secondZlt.calculate(obvVal);
	}
	
	/* (non-Javadoc)
	 * @see com.themachine.quantsystem.filter.Filter#getStatus()
	 */
	@Override
	public Status getStatus() {
		if (firstMa >= secondMa) {
			return Status.Bull;
		} else {
			return Status.Bear;
		}
	}
}
