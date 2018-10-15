package com.aakash.jenkins;

import static org.junit.Assert.*;

import org.junit.Test;

public class JenkinsSampleTest {

	@Test
	public void testMain() {
		JenkinsSample js =new JenkinsSample();
		int total =js.addition(3, 5);
		assertEquals(8,total);
		total =js.multiply(3, 9);
		assertNotEquals(8,total);
	}
}
