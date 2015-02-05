package kr.co.tmon.util;

import java.util.Random;
import java.util.Set;

import kr.co.tmon.data.message.MessageType;
import kr.co.tmon.data.message.QuestionType;

/**
 * 랜덤으로 메세지 대화 내용을 추출하는 유틸 클래스
 * 
 * @author 조우진
 * @version 0.1
 */
public class RandomMSGGenerator {

	/**
	 * 응답 메세지 집합을 입력받아 임의로 사용자에게 하나의 응답 메세지를 제공하는 메서드
	 * 
	 * @param msgSet
	 *            랜덤으로 생성할 응답 메세지 집합
	 * @return 랜덤으로 생성된 응답 메세지 문자열
	 */
	public static String getMSG(Set<String> msgSet) {
		int targetNum = new Random().nextInt(msgSet.size());
		int i = 0;
		for (String msg : msgSet) {
			if (i == targetNum) {
				return msg;
			}
			i++;
		}
		return null;
	}
}
