package kr.co.tmon.data.message;

/**
 * 사용자와 서버간의 주고 받을 대화 메세지와 메세지 타입을 담는 클래스 
 * 
 * @author 조우진
 * @version 0.1
 *
 */
public class ChatMessage {
	private String content;
	private MessageType type;

	public ChatMessage(String content, MessageType type) {
		super();
		this.content = content;
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ChatMessage [content=" + content + ", type=" + type + "]";
	}
}
