package kr.co.tmon.client;

import kr.co.tmon.data.message.ChatMessage;

/**
 * 사용자로부터 메세지를 입력받고, 사용자에게 응답메세지를 전달하기 위한 인터페이스 이다. 인터페이스를 생성한 이유는 사용자와의 접속환경에
 * 따라 Socket, HTTP, 콘솔 I/O등으로 메세지를 주고 받을 수 있기 때문이다.
 * 
 * @author 조우진
 * @version 0.1
 */
public interface ChatClient {

	/**
	 * 사용자와 커넥션을 맺음.
	 * 
	 * @return 정상적으로 연결되었을 경우 1을 반환, 연결에 실패 했을 경우 0을 반환함
	 */
	public int connect();

	/**
	 * 사용자의 이름을 입력받고, 기본 입출력 프롬프터 정보를 셋팅 등 채팅을 위한 초기화를 진행 한다.
	 */
	public void init();

	/**
	 * 사용자와의 커넥션은 안전하게 종료.
	 * 
	 * @return 정상적으로 연결되었을 경우 1을 반환, 연결에 실패 했을 경우 0을 반환함
	 */
	public int close();

	/**
	 * 사용자로부터 메세지 응답을 기다린다. 이 메서드는 동기적으로 작동하기 때문에 사용자의 응답이 올 떄가지 Blocking된다.
	 * 메시지가 도착하면 메세지를 멤버 객체에 보관하고 동시에 반환
	 * 
	 * @return 사용자로부터 입력받은 메세지를 ChatMessage 객체에 담아서 반환한다.
	 */
	public ChatMessage waitChatMessage();

	/**
	 * 사용자로부터 전달받은 객체를 반환
	 * 
	 * @return 사용자로부터 받은 ChatMessage 객체를 반환
	 */
	public ChatMessage getChatMessage();

	/**
	 * Server로부터 응답메세지를 받아서 사용자에게 전달한다.
	 * 
	 * @param message
	 *            Server에서 생성된 응답메시지
	 */
	public void respondToClient(ChatMessage message);
}
