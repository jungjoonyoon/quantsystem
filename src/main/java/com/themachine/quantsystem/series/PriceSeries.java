package com.themachine.quantsystem.series;

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

import com.themachine.quantsystem.entity.TradeBar;
import com.themachine.quantsystem.mapper.QuantMapper;
import com.themachine.quantsystem.util.DateUtil;

@Component
public class PriceSeries {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PriceSeries.class);
	
	@Autowired
	private QuantMapper quantMapper;
	
	private List<String> days = null;
	private Integer currentIdx = null;
	
	private Double currentGap = 0.0;
	private Double prevClose = null;
	

	/**
	 * @return
	 */
	public List<TradeBar> getHistory() {
		List<TradeBar> history = quantMapper.retrieveHistoryData();
		
		calculateGap(history);
		
		print(history);
		
		return history;
	}

	/**
	 * @return
	 */
	public boolean hasNextDay() {
		
		boolean result = false;
		
		if (days == null) {
			days = quantMapper.retrieveBacktestDays();
		}
		
		if (currentIdx == null) {
			currentIdx = 0;
			result = true;
		} else if (currentIdx != days.size() - 1) {
			++currentIdx;
			result = true;
		}
		
		return result;
	}

	/**
	 * @return
	 */
	public List<TradeBar> getNextData() {
		List<TradeBar> list = quantMapper.retrieveBacktestData(days.get(currentIdx));
		
		calculateGap(list);
		
		print(list);
		
		return list;
	}

	/**
	 * @param list
	 */
	private void calculateGap(List<TradeBar> list) {
		
		TradeBar prevBar = null;
		
		for (int idx = 0, cnt = list.size(); idx < cnt; ++idx) {
			TradeBar currBar = list.get(idx);
			
			if (idx == 0 && prevClose != null) {
				currentGap += currBar.getOpen() - prevClose;
			} else if (prevBar != null && 
					DateUtil.getDay(prevBar.getTime()) != DateUtil.getDay(currBar.getTime())) {
				Double gap = getGap(prevBar, currBar);
				currentGap += gap;
			}
			setGap(currBar, currentGap);
			prevBar = currBar;
			prevClose = currBar.getClose();
		}
	}

	/**
	 * @param tradeBar
	 */
	private void print(List<TradeBar> list) {
		
		List<String> lines = new ArrayList<>();
		
		for (TradeBar tradeBar : list) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String line = String.format("%s,%.2f,%.2f,%.2f,%.2f",
					format.format(tradeBar.getTime()),
					tradeBar.getOpen(),
					tradeBar.getClose(),
					tradeBar.getGapClose(),
					tradeBar.getGap()
			);
			lines.add(line);
		}
		
		try {
			FileUtils.writeLines(new File("gap.csv"), lines, true);
		} catch (IOException e) {
			LOGGER.error("Exception Occurred:", e);
		}
	}

	/**
	 * @param prevBar
	 * @param currBar
	 * @return
	 */
	private Double getGap(TradeBar prevBar, TradeBar currBar) {
		return currBar.getOpen() - prevBar.getClose();
	}

	/**
	 * @param currBar
	 * @param currentGap2
	 */
	private void setGap(TradeBar data, Double gap) {
		data.setGap(gap);
		data.setGapOpen (data.getOpen()  - gap);
		data.setGapHigh (data.getHigh()  - gap);
		data.setGapLow  (data.getLow()   - gap);
		data.setGapClose(data.getClose() - gap);
	}
}
