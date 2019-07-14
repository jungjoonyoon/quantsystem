package com.themachine.quantsystem.strategy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.themachine.quantsystem.entity.Position;
import com.themachine.quantsystem.entity.Position.PositionType;
import com.themachine.quantsystem.entity.TradeBar;
import com.themachine.quantsystem.filter.Filter.Status;
import com.themachine.quantsystem.filter.ObvZltFilter;
import com.themachine.quantsystem.filter.ZltMmaFilter;

@Component
public class Strategy {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Strategy.class);
	
	private List<Position> positionList = new ArrayList<>();
	private Position currentPosition;
	
	@Autowired
	private ZltMmaFilter zltMmaIndicator;
	
	@Autowired
	private ObvZltFilter obvZltIndicator;
	
	
	/**
	 * 
	 */
	public void initialize(List<TradeBar> history) {
		zltMmaIndicator.setFirstPeriod(110);
		zltMmaIndicator.setSecondPeriod(542);
		obvZltIndicator.setFirstPeriod(5);
		obvZltIndicator.setSecondPeriod(230);
		
		zltMmaIndicator.prepare(history);
		obvZltIndicator.prepare(history);
	}
	
	/**
	 * @param data
	 */
	public void onData(TradeBar data, int index) {
		
		zltMmaIndicator.onData(data);
		obvZltIndicator.onData(data);
		
		if (index >= 10) {
			if (zltMmaIndicator.getStatus() == Status.Bull &&
					obvZltIndicator.getStatus() == Status.Bull) {
				buy(data);
			} else if (zltMmaIndicator.getStatus() == Status.Bear &&
					obvZltIndicator.getStatus() == Status.Bear) {
				sell(data);
			}
		}
	}
	
	/**
	 * @return
	 */
	public Double profit() {
		Double result = 0.0;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		List<String> content = new ArrayList<>();
		
		for (Position position : positionList) {
			double profit = position.getType() == PositionType.Buy ? position.getExitPrice() - position.getEntryPrice() : position.getEntryPrice() - position.getExitPrice();
			result += profit;
			
//			String line = String.format("%s,%s,%s,%.2f,%s,%s,%.2f,%.2f",  
//					position.getType(),
//					format.format(position.getEntryTime()).substring(0, 10),
//					format.format(position.getEntryTime()).substring(10),
//					position.getEntryPrice(),
//					format.format(position.getExitTime()).substring(0, 10),
//					format.format(position.getExitTime()).substring(10),
//					position.getExitPrice(),
//					profit
//			);
//			
//			content.add(line);
		}
		
//		try {
//			FileUtils.writeLines(new File("profit.csv"), content);
//		} catch (IOException e) {
//			LOGGER.error("Exception Occurred:", e);
//		}
		
		return result;
	}
	
	/**
	 * @param data
	 */
	protected void buy(TradeBar data) {
		if (currentPosition != null && currentPosition.getType() == PositionType.Buy) {
//			LOGGER.info("Already having a buy position.");
			return;
		} else if (currentPosition != null && currentPosition.getType() == PositionType.Sell) {
			closePosition(data);
		}
		
		makePosition(PositionType.Buy, data);
	}

	/**
	 * @param data
	 */
	protected void sell(TradeBar data) {
		if (currentPosition != null && currentPosition.getType() == PositionType.Sell) {
//			LOGGER.info("Already having a sell position.");
			return;
		} else if (currentPosition != null && currentPosition.getType() == PositionType.Buy) {
			closePosition(data);
		}
		
		makePosition(PositionType.Sell, data);
	}
	
	/**
	 * @param data
	 */
	private void closePosition(TradeBar data) {
		currentPosition.setExitPrice(data.getClose());
		currentPosition.setExitTime(data.getTime());
		
		positionList.add(currentPosition);
	}

	/**
	 * @param type
	 * @param data
	 */
	private void makePosition(PositionType type, TradeBar data) {
		currentPosition = new Position();
		currentPosition.setEntryPrice(data.getClose());
		currentPosition.setEntryTime(data.getTime());
		currentPosition.setType(type);
	}
}
