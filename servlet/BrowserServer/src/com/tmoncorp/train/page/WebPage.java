package com.tmoncorp.train.page;

import java.util.Map;

public interface WebPage {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "fAIL";
	
	public String getResponse(Map<String, String> getParam);
	
	public String getPath();
}
