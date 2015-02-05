package kr.co.tmon.util;

import static org.junit.Assert.*;
import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.data.message.MessageType;

import org.junit.Test;

public class MessageAnalyzerTest {
	@Test
	public void test() {
		MessageAnalyzer analyzer = new MessageAnalyzer();
		
		ChatMessage msg1 = new ChatMessage("안녕 하니?", MessageType.Undecided);
		
		assertEquals(MessageType.Question, analyzer.anlayzeMessageType(msg1));
	}

}
