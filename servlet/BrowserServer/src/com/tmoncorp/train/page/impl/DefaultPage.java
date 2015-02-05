package com.tmoncorp.train.page.impl;

import java.util.Map;

import com.tmoncorp.train.page.WebPage;

public class DefaultPage implements WebPage {

	@Override
	public String getResponse(Map<String, String> getParam) {
		return "<h1>This is root Page!!</h1>";
	}

	@Override
	public String getPath() {
		return "/";
	}

}
