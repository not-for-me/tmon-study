package com.tmoncorp.train.page.impl;

import java.util.Map;

import com.tmoncorp.train.page.WebPage;

public class CalculatorPage implements WebPage {

	@Override
	public String getResponse(Map<String, String> getParam) {
		String operator = getParam.get("op");
		String opString = "+";
		int result = 0;
		switch (operator) {
		case "multiply":
			result = Integer.parseInt(getParam.get("var1"))
					* Integer.parseInt(getParam.get("var2"));
			opString = "*";
			break;
		case "divide":
			result = Integer.parseInt(getParam.get("var1"))
					/ Integer.parseInt(getParam.get("var2"));
			opString = "/";
			break;
		case "sub":
			result = Integer.parseInt(getParam.get("var1"))
					- Integer.parseInt(getParam.get("var2"));
			opString = "-";
			break;
		case "add":
		default:
			result = Integer.parseInt(getParam.get("var1"))
					+ Integer.parseInt(getParam.get("var2"));
		}

		StringBuffer response = new StringBuffer();
		response.append(
				"<h2 style='color:#CE8FE6'>Here is Calculator Page</h2>")
				.append("<p>");
		response.append(getParam.get("var1")).append(" " + opString + " ")
				.append(getParam.get("var2")).append(" = ").append(result)
				.append("</p>");

		return response.toString();
	}

	@Override
	public String getPath() {
		return "/calc";
	}

}
