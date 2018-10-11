package com.aakash.jenkins;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class JenkinsSampleTest {

	@Test
	public void testMain() {
		int total =JenkinsSample.addition(3, 5);
		assertEquals(8,total);
		total =JenkinsSample.addition(3, 9);
		assertNotEquals(8,total);
	}
}
