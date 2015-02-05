package kr.co.tmon.server.analytics;

import static org.junit.Assert.assertEquals;
import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.data.message.MessageType;
import kr.co.tmon.util.MessageAnalyzer;

import org.junit.Before;
import org.junit.Test;

public class MessageAnalyzerTest {
	private MessageAnalyzer analyzer;
	
	@Before
	public void setUp() {
		analyzer = new MessageAnalyzer();
	}
	
	@Test
	public void mesageAnalyzerTest() {
		
		
		ChatMessage msg2 = new ChatMessage("오늘 날씨?", MessageType.Undecided);
		assertEquals(MessageType.Question, analyzer.anlayzeMessageType(msg2));
		
		ChatMessage msg3 = new ChatMessage("밥은 먹었니?", MessageType.Undecided);
		assertEquals(MessageType.Question, analyzer.anlayzeMessageType(msg3));
		
		
	}
}
