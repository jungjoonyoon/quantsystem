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
public class ObvTest {
	
	@Autowired
	private QuantMapper quantMapper;
	
	private List<TradeBar> history;
	private Obv obv;

	@Before
	public void setUp() throws Exception {
		obv = new Obv();
		history = quantMapper.retrieveHistoryData();
	}

	@Test
	public void testCalculate() {
		for (int idx = 0, cnt = history.size(); idx < cnt; ++idx) {
			TradeBar tradeBar = history.get(idx);
			Double result = obv.calculate(tradeBar);
			
			if (idx == 120) {
				assertEquals(3337, result, 0.001);
			} else if (idx == 160) {
				assertEquals(27131, result, 0.001);
			} else if (idx == 200) {
				assertEquals(19368, result, 0.001);
			} else if (idx == 250) {
				assertEquals(31332, result, 0.001);
			} else if (idx == 280) {
				assertEquals(29493, result, 0.001);
			}
		}
	}

}
