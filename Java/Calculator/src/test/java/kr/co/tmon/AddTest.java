package kr.co.tmon;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddTest {

	@Test
	public void test() {
		Calculator calc = new Calculator();

		assertEquals(2, calc.add(1));
	}

}
