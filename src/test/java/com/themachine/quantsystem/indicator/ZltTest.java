package com.themachine.quantsystem.indicator;

import static org.junit.Assert.*;

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
public class ZltTest {
	
	@Autowired
	private QuantMapper quantMapper;
	
	private List<TradeBar> history;
	private Zlt zlt;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		zlt = new Zlt(25);
		history = quantMapper.retrieveHistoryData();
	}

	/**
	 * 
	 */
	@Test
	public void testCalculate() {
		for (int idx = 0, cnt = history.size(); idx < cnt; ++idx) {
			TradeBar tradeBar = history.get(idx);
			Double result = zlt.calculate(tradeBar);
			
			if (idx == 120) {
				assertEquals(86.35828805, result, 0.001);
			} else if (idx == 160) {
				assertEquals(87.94280468, result, 0.001);
			} else if (idx == 200) {
				assertEquals(87.76969501, result, 0.001);
			} else if (idx == 250) {
				assertEquals(88.43672762, result, 0.001);
			} else if (idx == 280) {
				assertEquals(89.83310499, result, 0.001);
			}
		}
	}

}
