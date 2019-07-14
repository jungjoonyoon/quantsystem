package com.themachine.quantsystem;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.themachine.quantsystem.entity.TradeBar;
import com.themachine.quantsystem.series.PriceSeries;
import com.themachine.quantsystem.strategy.Strategy;

@Component
public class QuantSystemRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuantSystemRunner.class);
	
	@Autowired
	private PriceSeries priceSeries;
	
	@Autowired
	private Strategy strategy;

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... arg0) throws Exception {
		strategy.initialize(priceSeries.getHistory());
		
		while (priceSeries.hasNextDay()) {
			List<TradeBar> list = priceSeries.getNextData();
			
			for (int idx = 0, cnt = list.size(); idx < cnt; ++idx) {
				TradeBar tradeBar = list.get(idx);
				strategy.onData(tradeBar, idx);
			}
		}
		
		LOGGER.info("profit: {}", strategy.profit());
	}
}
