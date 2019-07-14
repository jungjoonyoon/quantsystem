package com.themachine.quantsystem.filter;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.themachine.quantsystem.entity.TradeBar;
import com.themachine.quantsystem.filter.Filter.Status;
import com.themachine.quantsystem.mapper.QuantMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ZltMmaFilterTest {
	
	@Autowired
	private QuantMapper quantMapper;
	
	@Autowired
	private ZltMmaFilter zltMmaFilter;
	private List<TradeBar> history;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		zltMmaFilter.setFirstPeriod(25);
		zltMmaFilter.setSecondPeriod(60);
		history = quantMapper.retrieveHistoryData();
	}

	/**
	 * 
	 */
	@Test
	public void test1Prepare() {
		zltMmaFilter.prepare(history);
		
		assertEquals(Status.Bull, zltMmaFilter.getStatus());
	}

	/**
	 * 
	 */
	@Test
	public void test2OnData() {
		
		String testDay = "20030718";
		
		List<TradeBar> list = quantMapper.retrieveBacktestData(testDay);
		
		for (int idx = 0, cnt = list.size(); idx < cnt; ++idx) {
			TradeBar tradeBar = list.get(idx);
			zltMmaFilter.onData(tradeBar);
			
			if (idx == 10) {
				assertEquals(Status.Bear, zltMmaFilter.getStatus());
			} else if (idx == 30) {
				assertEquals(Status.Bull, zltMmaFilter.getStatus());
			} else if (idx == 60) {
				assertEquals(Status.Bear, zltMmaFilter.getStatus());
			} else if (idx == 100) {
				assertEquals(Status.Bear, zltMmaFilter.getStatus());
			} else if (idx == 130) {
				assertEquals(Status.Bull, zltMmaFilter.getStatus());
			}
		}
	}

}
