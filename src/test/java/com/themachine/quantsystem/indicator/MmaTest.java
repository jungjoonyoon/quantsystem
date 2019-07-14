package com.themachine.quantsystem.indicator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.themachine.quantsystem.entity.TradeBar;
import com.themachine.quantsystem.mapper.QuantMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MmaTest {
	
	@Autowired
	private QuantMapper quantMapper;
	
	private List<TradeBar> history;
	private Mma mma;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		mma = new Mma(60);
		history = quantMapper.retrieveHistoryData();
	}

	/**
	 * 
	 */
	@Test
	public void testCalculate() {
		for (int idx = 0, cnt = history.size(); idx < cnt; ++idx) {
			TradeBar tradeBar = history.get(idx);
			Double result = mma.calculate(tradeBar);
			
			if (idx == 120) {
				assertEquals(86.11377049, result, 0.001);
			} else if (idx == 160) {
				assertEquals(87.75278689, result, 0.001);
			} else if (idx == 200) {
				assertEquals(88.08726776, result, 0.001);
			} else if (idx == 250) {
				assertEquals(88.25666667, result, 0.001);
			} else if (idx == 280) {
				assertEquals(88.81822404, result, 0.001);
			}
		}
	}

}
