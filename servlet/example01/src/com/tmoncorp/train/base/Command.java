package com.tmoncorp.train.base;

public interface Command {
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";

	String exeucte();

}
