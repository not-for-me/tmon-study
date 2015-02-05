package kr.co.tmon.client.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import kr.co.tmon.client.ChatClient;
import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.data.message.MessageType;

/**
 * 콘솔의 IO를 통해서 사용자와 데이터를 주고받기 위한 ChatClient의 구현체
 * 
 * @see ChatClient
 * @author 조우진
 * @version 0.1
 */
public class ConsoleChatClientImpl implements ChatClient {
	private ChatMessage userMsg;
	private BufferedReader br;
	private String name;
	private String clientPrompt;
	private String serverPrompt;

	/**
	 * 멤버 변수들을 초기화
	 */
	public ConsoleChatClientImpl() {
		name = "";
		clientPrompt = "";
		serverPrompt = "";
	}

	/**
	 * 콘솔 입출력을 위해서 BufferedReader를 활용하여 스트림을 생성
	 * 
	 * @see BufferedReader
	 */
	@Override
	public int connect() {
		br = new BufferedReader(new InputStreamReader(System.in));
		if (br == null) {
			return 0;
		}
		return 1;
	}

	/**
	 * 사용자로부터 이름을 입력받고 대화창에 출력할 프롬프터를 생성
	 */
	@Override
	public void init() {
		requireUserName();
		setPrompter();
	}

	/**
	 * 사용자로부터 이름을 입력 받음
	 */
	private void requireUserName() {
		System.out.print("사용자 이름을 입력해 주세요: ");
		try {
			name = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 대화창에 출력할 프롬프터 설정
	 */
	private void setPrompter() {
		clientPrompt = new StringBuilder().append("(").append(name)
				.append(")> ").toString();
		serverPrompt = "(ChatBot)> ";
	}

	/**
	 * 콘솔로 사용자와 대화를 주고 받는 스트림을 닫음
	 * 
	 * @see BufferedReader
	 */
	@Override
	public int close() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	/**
	 * 사용자로부터 대화 메세지가 올때까지 대기한다. 대화 메세지가 도착하면 멤버 변수에 담고 동시에 이를 반환
	 * 
	 * @return 사용자로부터 수신된 메시지를 ChatMessage객체에 담아 반환
	 */
	@Override
	public ChatMessage waitChatMessage() {
		String content = requestClientChat();
		if (content != null) {
			userMsg = new ChatMessage(content, MessageType.Undecided);
			return userMsg;
		} else {
			return null;
		}
	}

	/**
	 * 사용자로부터 수신한 메세지를 반환
	 * 
	 * @return 사용자로부터 수신된 메시지를 ChatMessage객체에 담아 반환
	 */
	@Override
	public ChatMessage getChatMessage() {
		return userMsg;
	}

	/**
	 * 사용자로부터 스트림을 통해 대화메세지를 수신
	 * 
	 * @return 사용자로부터 수신 받은 대화메세지
	 */
	private String requestClientChat() {
		System.out.print(clientPrompt);
		String content = null;
		try {
			content = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			content = null;
		}
		return content;
	}

	/**
	 * 서버로부터 받은 응답 메세지를 사용자에게 전달하여 콘솔로 출력
	 * 
	 * @param message
	 *            사용자에게 전달한 메세지 객체
	 */
	@Override
	public void respondToClient(ChatMessage message) {
		System.out.print(serverPrompt);
		System.out.println(message.getContent());
	}

}
