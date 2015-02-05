package com.tmoncorp.train.command;

import com.tmoncorp.train.base.Command;

public class HelloWorldCommand implements Command {

	@Override
	public String exeucte() {
		System.out.println("Hello, World");
		return SUCCESS;
	}

}
