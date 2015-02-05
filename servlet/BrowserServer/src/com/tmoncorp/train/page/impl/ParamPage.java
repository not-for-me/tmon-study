package com.tmoncorp.train.page.impl;

import java.util.Map;

import com.tmoncorp.train.page.WebPage;

public class ParamPage implements WebPage {

	@Override
	public String getResponse(Map<String, String> getParam) {
		StringBuffer response = new StringBuffer();
		response.append("<h2 style='color:#CE8FE6'>Here is Param Page</h2>").append("<ul>");
		for (String paramKey : getParam.keySet()) {
			response.append("<li>Your request param key: ")
					.append(paramKey + " / ").append("param value: ")
					.append(getParam.get(paramKey)+"</li>");
		}
		response.append("</ul>");
		return response.toString();
	}

	@Override
	public String getPath() {
		return "/param";
	}

}
