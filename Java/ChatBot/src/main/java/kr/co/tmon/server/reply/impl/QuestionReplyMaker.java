package kr.co.tmon.server.reply.impl;

import java.util.Set;

import kr.co.tmon.data.ChatBotDataSet;
import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.data.message.MessageType;
import kr.co.tmon.data.message.QuestionType;
import kr.co.tmon.server.reply.ReplyMsgMaker;
import kr.co.tmon.util.MessageAnalyzer;
import kr.co.tmon.util.RandomMSGGenerator;

/**
 * 사용자의 질문에 응답할 메시지를 생성하는 구현
 * 
 * @author 조우진
 * @version 0.1
 * @see ReplyMsgMaker
 * @see MessageAnalyzer
 * @see MessageType
 * @see RandomMSGGenerator
 */
public class QuestionReplyMaker implements ReplyMsgMaker {

	/**
	 * dataset.json 파일에서 로드한 일반적인 응답 내용 중에서 랜덤으로 반환함
	 * 
	 * @param userMsg
	 *            사용자 메세지에서 질문의 키워드를 확인하기 위해서 필요한 파라미터
	 * @return 생성된 응답메세지를 반환
	 */
	public ChatMessage makeReplyMessage(ChatMessage userMsg) {
		String replyMsg = null;
		QuestionType qt = MessageAnalyzer.anlayzeQuestionType(userMsg);
		if (qt == QuestionType.Known) {
			replyMsg = getReserverdReplyMsg(userMsg);
		} else if (qt == QuestionType.Unknown) {
			replyMsg = RandomMSGGenerator
					.getMSG(ChatBotDataSet.unreservedSet);
		}

		return new ChatMessage(replyMsg, MessageType.Question);
	}

	private String getReserverdReplyMsg(ChatMessage userMsg) {
		Set<String> reservedKeywordSet = ChatBotDataSet.reservedDataMap
				.keySet();
		for (String keyword : reservedKeywordSet) {
			if (userMsg.getContent().contains(keyword)) {
				return RandomMSGGenerator.getMSG(ChatBotDataSet.reservedDataMap
						.get(keyword));
			}
		}
		return null;
	}

}
