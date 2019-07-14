package com.themachine.quantsystem.indicator;

import java.util.ArrayList;
import java.util.List;

import com.themachine.quantsystem.entity.TradeBar;

public class Mma implements Indicator {

	private int period;
	private List<Double> history = new ArrayList<>();
	private Double tn = 0.0;
	private Double s1 = 0.0;

	/**
	 * @param period
	 */
	public Mma(int period) {
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
		return calculateMma(data);
	}
	
//	   outIdx = 0;
//	   s1 = 0; tn = 0;
//
//	   for ( i = startIdx; i <= endIdx; i++ ) 
//	   {
//	      if (i == startIdx)
//	      {
//	         /* Calculate s1, tn for the initial period */
//	         for ( j = 0; j < optInTimePeriod; j++ )
//	         {
//	            /* s1 = s1 + ((Period - 2 * Count - 1) / 2) * Price[Count];  */
//	            s1 = s1 + ((optInTimePeriod - 2.0 * (double)j - 1.0) / 2.0) * input[startIdx - j];
//	            tn += input[startIdx - j];
//	         }
//	         tn = tn / (double)optInTimePeriod;
//	      }
//
//	      if (i > startIdx)
//	      {
//	         /* s1 = s1[1] - tn[1] * Period + ((Period - 1) / 2) * Price + ((Period + 1) / 2) * Price[Period]; */
//	         s1 = s1 - tn * optInTimePeriod + ((optInTimePeriod - 1.0) / 2.0) * input[i] + ((optInTimePeriod + 1.0) / 2.0) * input[i - optInTimePeriod];
//	         tn = (tn * (double)optInTimePeriod + input[i] - input[i - optInTimePeriod]) / (double)optInTimePeriod;
//	      }
//
//	      /* MMA = tn + (6 * s1)/((Period + 1) * Period); */
//	      outReal[outIdx++] = tn + (6.0 * s1) / ((optInTimePeriod + 1.0) * optInTimePeriod);
//	   }
	
	/**
	 * @param price
	 * @return
	 */
	private Double calculateMma(Double price) {
		history.add(price);
		
		if (history.size() < period) {
			
			return price;
		} else if (history.size() == period) {
			
			for (int j = 0; j < period; j++) {
				/* s1 = s1 + ((Period - 2 * Count - 1) / 2) * Price[Count];  */
	            s1 = s1 + ((period - 2.0 * (double)j - 1.0) / 2.0) * history.get(period - j - 1);
	            tn += history.get(period - j - 1);
			}
	         tn = tn / (double)period;
		} else {
			/* s1 = s1[1] - tn[1] * Period + ((Period - 1) / 2) * Price + ((Period + 1) / 2) * Price[Period]; */
			s1 = s1 - tn * period + ((period - 1.0) / 2.0) * price + ((period + 1.0) / 2.0) * history.get(0);
			tn = (tn * (double)period + price - history.get(0)) / (double)period;
			
			history.remove(0);
		}
		
	    /* MMA = tn + (6 * s1)/((Period + 1) * Period); */
		return tn + (6.0 * s1) / ((period + 1.0) * period);
	}

}
