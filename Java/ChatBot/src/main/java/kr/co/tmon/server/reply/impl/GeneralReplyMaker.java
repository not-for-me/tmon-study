package kr.co.tmon.server.reply.impl;

import java.util.Random;

import kr.co.tmon.data.ChatBotDataSet;
import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.data.message.MessageType;
import kr.co.tmon.server.reply.ReplyMsgMaker;
import kr.co.tmon.util.RandomMSGGenerator;

/**
 * 사용자에게 일반적인 이야기를 응답할 메시지를 생성하는 구현
 * 
 * @author 조우진
 * @version 0.1
 * @see ReplyMsgMaker
 * @see MessageType
 * @see RandomMSGGenerator
 */
public class GeneralReplyMaker implements ReplyMsgMaker {

	/**
	 * dataset.json 파일에서 로드한 일반적인 응답 내용 중에서 랜덤으로 반환함
	 * 
	 * @param userMsg
	 *            사용자 메세지를 반복해서 반응할 때 필요한 파라미터
	 * @return 생성된 응답메세지를 반환
	 */
	public ChatMessage makeReplyMessage(ChatMessage userMsg) {
		return getRandomizedReplyMessage(userMsg);
	}

	/**
	 * 사용자에게 일반적인 응답 메세지로 간단한 답변을 제공하거나, 사용자의 대화 내용을 그대로 반복해주는 메세지를 임의로 생성해서 제공
	 * 
	 * @return 생성된 응답메세지를 반환
	 */
	private ChatMessage getRandomizedReplyMessage(ChatMessage userMsg) {
		int size = 2;
		int num = new Random().nextInt(size);

		if (num == 0) {
			return buildSimpleReplyMessage();
		} else {
			return duplicateClientMessage(userMsg);
		}
	}

	private ChatMessage buildSimpleReplyMessage() {
		String replyMessage = RandomMSGGenerator
				.getMSG(ChatBotDataSet.simpleReplySet);
		return new ChatMessage(replyMessage, MessageType.General);
	}

	private ChatMessage duplicateClientMessage(ChatMessage userMsg) {
		String replyMessage = RandomMSGGenerator
				.getMSG(ChatBotDataSet.duplicateReplySet);
		return new ChatMessage(replyMessage + userMsg.getContent(),
				MessageType.General);
	}
}
