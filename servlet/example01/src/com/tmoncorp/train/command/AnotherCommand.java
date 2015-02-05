package com.tmoncorp.train.command;

import com.tmoncorp.train.base.Command;

public class AnotherCommand implements Command {

	@Override
	public String exeucte() {
		System.out.println("This is anothercommand");
		return FAIL;
	}

}
