package kr.co.tmon;

import kr.co.tmon.client.ChatClient;
import kr.co.tmon.client.impl.ConsoleChatClientImpl;
import kr.co.tmon.data.ChatBotDataSet;
import kr.co.tmon.data.message.ChatMessage;
import kr.co.tmon.server.ChatServer;

/**
 * ChatBot의 실행 진입점인 main함수가 포함된 클래스이다. Client와 Server 객체를 멤버로 갖고 있으며 ChatBot
 * 프로그램의 실행주기를 관리한다.
 * 
 * @author 조우진
 * @version 0.1
 */
public class ChatBot {
	private ChatClient chatClient;
	private ChatServer chatServer;

	public static void main(String[] args) {
		ChatBot chatbot = new ChatBot();
		chatbot.initChatBot();
		chatbot.runChat();
	}

	/**
	 * ChatBot 실행을 위해서 Client와 Server 객체를 초기화하고 대화에 필요한 기본 데이터셋을 불러온다.
	 */
	private void initChatBot() {
		chatClient = new ConsoleChatClientImpl();
		chatServer = new ChatServer();
		// TODO 데이터로드하는 코드를 DB나 Web에서도 불러올 수 있도록 인터페이스화 해야
		ChatBotDataSet.loadDataSet(this.getClass().getClassLoader()
				.getResource("dataset.json"));
	}

	/**
	 * ChatBot을 실행한다. ChatBot은 사용자로부터 'q' 입력을 받을 때 프로그램이 정상 종료한다.
	 * 
	 * @see ChatServer
	 * @see ChatClient
	 * @see ChatMessage
	 */
	private void runChat() {
		System.out.println("Welcome to ChatBot!!!");
		chatClient.connect();
		chatClient.init();
		
		boolean isFin = false;
		while (!isFin) {
			if (chatClient.waitChatMessage().getContent().equals("q")) {
				isFin = true;
			}

			if (!isFin) {
				ChatMessage replyMsg = processInServer(chatClient
						.getChatMessage());
				chatClient.respondToClient(replyMsg);
			}
		}

		System.out.println("Finish to ChatBot!!!");
	}

	/**
	 * Server에서 사용자로부터 받은 대화 메세지를 처리하여 응답 메세지를 만든다. 처리 과정은 1. 메세지 타입 분석, 2.
	 * 응답메세지 생성 3. 응답 메세지 반환으로 이루어진다.
	 * 
	 * @see ChatServer
	 * @see ChatMessage
	 * @param userMsg
	 *            Client에서 사용자로부터 입력받은 메세지
	 * @return Server에서 사용자에게 전달하기 위해 생성한 응답 메세지
	 */
	private ChatMessage processInServer(ChatMessage userMsg) {
		chatServer.sendUserMsg(userMsg);

		chatServer.anaylzeMessageType();

		chatServer.makeReplyMessage();

		return chatServer.getChatMessage();
	}

}
