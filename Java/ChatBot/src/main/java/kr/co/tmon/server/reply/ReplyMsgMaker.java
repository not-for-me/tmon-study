package kr.co.tmon.server.reply;

import kr.co.tmon.data.message.ChatMessage;

/**
 * 사용자에게 응답할 메시지 반환 인터페이스
 * 
 * @author 조우진
 * @version 0.1
 * @see ReplyMsgMaker
 */

public interface ReplyMsgMaker {

	/**
	 * 사용자에게 응답할 메세지를 생성.
	 * 
	 * @param userMsg
	 *            응답 메세지에서 필요한 경우를 대비하여 받는 사용자 메세지
	 * @return 생성된 응답메세지를 반환
	 */
	public ChatMessage makeReplyMessage(ChatMessage userMsg);
}
