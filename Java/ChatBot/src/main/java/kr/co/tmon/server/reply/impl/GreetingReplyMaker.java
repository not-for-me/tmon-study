package kr.co.tmon.server.reply.impl;

import kr.co.tmon.data.ChatBotDataSet;
import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.data.message.MessageType;
import kr.co.tmon.server.reply.ReplyMsgMaker;
import kr.co.tmon.util.RandomMSGGenerator;

/**
 * 사용자에게 인사말로 응답할 메시지를 생성하는 구현
 * 
 * @author 조우진
 * @version 0.1
 * @see ReplyMsgMaker
 * @see MessageType
 * @see RandomMSGGenerator
 * 
 */
public class GreetingReplyMaker implements ReplyMsgMaker {

	/**
	 * dataset.json 파일에서 로드한 인사말 가운데 랜덤으로 반환함
	 * 
	 * @param userMsg
	 *            사용하지않는 파라미터
	 * @return 생성된 응답메세지를 반환
	 */
	public ChatMessage makeReplyMessage(ChatMessage userMsg) {
		String replyMessage = RandomMSGGenerator
				.getMSG(ChatBotDataSet.greetReplySet);
		return new ChatMessage(replyMessage, MessageType.Greeting);
	}
}
