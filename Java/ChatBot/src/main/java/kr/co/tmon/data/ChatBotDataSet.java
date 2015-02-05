package kr.co.tmon.data;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * dataset.json 파일의 데이터를 메모리에 보관하기 위한 스태틱 클래스
 * 
 * @author 조우진
 * @version 0.1
 *
 */
public class ChatBotDataSet {
	private static ObjectMapper mapper;
	private static JsonNode rootNode;
	public static Set<String> greetReplySet;
	public static Set<String> simpleReplySet;
	public static Set<String> duplicateReplySet;
	public static Map<String, Set<String>> reservedDataMap;
	public static Set<String> unreservedSet;

	/**
	 * dataset.json 파일의 경로를 입력 받아 데이터를 로드하는 메서드 
	 * @param filePath dataset.json 파일의 URL
	 * @return 성공적으로 데이터를 메모리에 적재했을 경우 1을 반환, 그렇지 않으면 0을 반환
	 */
	public static int loadDataSet(URL filePath) {
		mapper = new ObjectMapper();
		try {
			rootNode = mapper.readTree(filePath);
		} catch (IOException e) {
			return 0;
		}

		loadAllData();
		return 1;
	}
	
	/**
	 * 각 그룹별 데이터를 로드 
	 */
	private static void loadAllData() {
		getGreetReplyData();
		getSimpleReplyData();
		getDuplicateReplyData();
		getKnownReplyData();
		getUnknownReplyData();
	}
	
	private static void getGreetReplyData() {
		greetReplySet = new HashSet<String>();
		
		for (JsonNode msg : rootNode.path("greet-list")) {
			greetReplySet.add(msg.textValue());
		}
	}

	private static void getSimpleReplyData() {
		simpleReplySet = new HashSet<String>();
		
		for (JsonNode msg : rootNode.path("simple-list")) {
			simpleReplySet.add(msg.textValue());
		}
	}

	private static void getDuplicateReplyData() {
		duplicateReplySet = new HashSet<String>();

		for (JsonNode msg : rootNode.path("duplicate-list")) {
			duplicateReplySet.add(msg.textValue());
		}
	}

	private static void getKnownReplyData() {
		reservedDataMap = new HashMap<String, Set<String>>();

		for (JsonNode reservedNode : rootNode.path("reserved-list")) {
			Set<String> replySet = new HashSet<String>();
			
			for (JsonNode replyValueNode : reservedNode.path("reply-list")) {
				replySet.add(replyValueNode.textValue());
			}
			reservedDataMap.put(reservedNode.path("key").textValue(), replySet);
		}
	}

	private static void getUnknownReplyData() {
		unreservedSet = new HashSet<String>();

		for (JsonNode msg : rootNode.path("unknown-list")) {
			unreservedSet.add(msg.textValue());
		}
	}

}
