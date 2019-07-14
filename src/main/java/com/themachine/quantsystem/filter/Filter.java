package com.themachine.quantsystem.filter;

import java.util.List;

import com.themachine.quantsystem.entity.TradeBar;

public interface Filter {
	
	enum Status { Bull, Bear }

	Status getStatus();

	void onData(TradeBar data);

	void prepare(List<TradeBar> history);;
	
}
