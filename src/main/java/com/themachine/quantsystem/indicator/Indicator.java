package com.themachine.quantsystem.indicator;

import com.themachine.quantsystem.entity.TradeBar;

public interface Indicator {
	
	Double calculate(TradeBar data);
	
	Double calculate(Double data);
	
}
