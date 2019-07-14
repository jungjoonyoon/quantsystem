package com.themachine.quantsystem.filter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.themachine.quantsystem.entity.TradeBar;
import com.themachine.quantsystem.indicator.Mma;
import com.themachine.quantsystem.indicator.Zlt;

@Component
public class ZltMmaFilter implements Filter {
	
	private int firstPeriod;
	private int secondPeriod;
	private Double firstMa;
	private Double secondMa;
	
	private Zlt zlt;
	private Mma mma;
	

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
		
		zlt = new Zlt(firstPeriod);
		mma = new Mma(secondPeriod);
		
		for (TradeBar tradeBar : history) {
			firstMa = zlt.calculate(tradeBar.getGapClose());
			secondMa = mma.calculate(tradeBar.getGapClose());
		}
	}

	/* (non-Javadoc)
	 * @see com.themachine.quantsystem.filter.Filter#onData(com.themachine.quantsystem.entity.TradeBar)
	 */
	@Override
	public void onData(TradeBar data) {
		firstMa = zlt.calculate(data.getGapClose());
		secondMa = mma.calculate(data.getGapClose());
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
