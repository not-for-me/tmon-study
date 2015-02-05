package kr.co.tmon.util;

import kr.co.tmon.data.ChatBotDataSet;
import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.data.message.MessageType;
import kr.co.tmon.data.message.QuestionType;

/**
 * 사용자의 대화 메세지를 정규식으로 파싱하여 특정 키워드에 따라 메세지의 타입을 분류하는 유틸 클래스
 * 
 * @author 조우진
 * @version 0.1
 * @see MessageType
 * @see QuestionType
 */
public class MessageAnalyzer {
	private final static String QUESTION_PATTERN = "(.*)(\\?)(.*)|알아|알려줘|궁금|뭐|아니";
	private final static String GREETING_PATTERN = ".*안녕.*|.*하이.*|.*반가.*";

	/**
	 * 사용자로부터 대화 메세지를 입력받아 메세지 내용의 특정 키워드에 따라 메세지 타입을 반환하는 메서드
	 * 
	 * @param userMsg
	 *            사용자의 대화 메세지
	 * @return 사용자 메세지 내용에 따라 결정된 메세지타입
	 */
	public static MessageType anlayzeMessageType(ChatMessage userMsg) {
		if (userMsg.getContent().matches(QUESTION_PATTERN)) {
			return MessageType.Question;
		} else if (userMsg.getContent().matches(GREETING_PATTERN)) {
			return MessageType.Greeting;
		} else {
			return MessageType.General;
		}
	}

	/**
	 * 사용자의 질문 메세지가 ChatBot 시스템 내 예측 답변 리스트로 답할 수 있는 질문인지 아닌지를 결정하여 해당하는
	 * QuestionType 타입을 반환하는 메서드
	 * 
	 * @param userMsg
	 *            사용자의 질문 메세지
	 * @return 사용자 질문 메세지 내용에 따라 결정된 질문타입
	 */
	public static QuestionType anlayzeQuestionType(ChatMessage userMsg) {
		for (String keyword : ChatBotDataSet.reservedDataMap.keySet()) {
			if (userMsg.getContent().contains(keyword)) {
				return QuestionType.Known;
			}
		}

		return QuestionType.Unknown;
	}

}
