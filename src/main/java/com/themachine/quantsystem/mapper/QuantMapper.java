package com.themachine.quantsystem.mapper;

import java.util.List;

import com.themachine.quantsystem.entity.TradeBar;

public interface QuantMapper {

	List<TradeBar> retrieveHistoryData();

	List<String> retrieveBacktestDays();

	List<TradeBar> retrieveBacktestData(String date);

}
