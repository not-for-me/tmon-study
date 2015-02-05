package kr.co.tmon.client;

import kr.co.tmon.client.impl.ConsoleChatClientImpl;

import org.junit.Before;
import org.junit.Test;

public class ChatClientTest {
	private ChatClient client;
	
	@Before
	public void setUp() {
		client = new ConsoleChatClientImpl();
	}
	
	@Test
	public void test() {
		
	}
}
