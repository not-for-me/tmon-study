package kr.co.tmon.server;

import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.data.message.MessageType;
import kr.co.tmon.server.reply.ReplyMsgMaker;
import kr.co.tmon.server.reply.impl.GeneralReplyMaker;
import kr.co.tmon.server.reply.impl.GreetingReplyMaker;
import kr.co.tmon.server.reply.impl.QuestionReplyMaker;
import kr.co.tmon.util.MessageAnalyzer;

/**
 * 사용자로부터 수신한 메세지를 분석해서 관련있응답을 전달할 서버 구현체
 * 
 * @author 조우진
 * @version 0.1
 * @see ChatMessage
 * @see MessageAnalyzer
 * @see MessageType
 * @see ReplyMsgMaker
 */
public class ChatServer {
	private ChatMessage userMsg;
	private ChatMessage replyMsg;
	private MessageType userMsgType;
	private ReplyMsgMaker replyMsgMaker;

	/**
	 * 사용자 메세지 분석결과가 담길 메세지 타입 변수를 초기화
	 * @see MessageAnalyzer
	 * @see MessageType
	 */
	public ChatServer() {
		userMsgType = MessageType.Undecided;
	}
	
	/**
	 * 사용자 메세지를 전달받아 메세지 내용을 저장
	 * 
	 * @see ChatMessage
	 * @param userMsg
	 *            사용자로부터 전달받은 메세지
	 */
	public void sendUserMsg(ChatMessage userMsg) {
		this.userMsg = userMsg;
	}

	/**
	 * 사용자 메세지를 전달받아 메세지 내용을 분석하여 메세지 타입을 결정
	 * 
	 * @see ChatMessage
	 * @see MessageAnalyzer
	 * @param userMsg
	 *            사용자로부터 전달받은 메세지
	 */
	public void anaylzeMessageType() {
		userMsgType = MessageAnalyzer.anlayzeMessageType(userMsg);
	}

	/**
	 * 사용자에게 전달할 응답 메시지를 작성하여 반환한다.
	 * @see MessateType
	 * @see ReplyMsgMaker
	 */
	public void makeReplyMessage() {
		if (userMsgType == MessageType.Question) {
			replyMsgMaker = new QuestionReplyMaker();
		} else if (userMsgType == MessageType.General) {
			replyMsgMaker = new GeneralReplyMaker();
		} else {
			replyMsgMaker = new GreetingReplyMaker();
		}

		replyMsg = replyMsgMaker.makeReplyMessage(userMsg);
	}

	/**
	 * @see ChatMessage
	 * @return 생성된 응답 메시지를 반환
	 */
	public ChatMessage getChatMessage() {
		return replyMsg;
	}

}
