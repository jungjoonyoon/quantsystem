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
public class ObvZltFilterTest {
	
	@Autowired
	private QuantMapper quantMapper;
	
	@Autowired
	private ObvZltFilter obvZltFilter;
	private List<TradeBar> history;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		obvZltFilter.setFirstPeriod(25);
		obvZltFilter.setSecondPeriod(60);
		history = quantMapper.retrieveHistoryData();
	}

	/**
	 * 
	 */
	@Test
	public void test1Prepare() {
		obvZltFilter.prepare(history);
		
		assertEquals(Status.Bull, obvZltFilter.getStatus());
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
			obvZltFilter.onData(tradeBar);
			
			if (idx == 10) {
				assertEquals(Status.Bear, obvZltFilter.getStatus());
			} else if (idx == 30) {
				assertEquals(Status.Bull, obvZltFilter.getStatus());
			} else if (idx == 60) {
				assertEquals(Status.Bear, obvZltFilter.getStatus());
			} else if (idx == 100) {
				assertEquals(Status.Bear, obvZltFilter.getStatus());
			} else if (idx == 126) {
				assertEquals(Status.Bear, obvZltFilter.getStatus());
			} else if (idx == 130) {
				assertEquals(Status.Bull, obvZltFilter.getStatus());
			}
		}
	}

}
