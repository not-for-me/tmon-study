package kr.co.tmon.data;

import org.junit.Before;
import org.junit.Test;

public class ChatBotDataSetTest {
	
	@Before
	public void setUp() {
		ChatBotDataSet.loadDataSet(this.getClass().getClassLoader()
				.getResource("dataset.json"));
	}

	@Test
	public void getChatBotDataSetTest() {
	}
}
