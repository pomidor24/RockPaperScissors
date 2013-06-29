package com.drapeko.rps.rest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestTrueResponse {

	@Test
	public void testTrueResponse() {
		TrueResponse response = new TrueResponse();
		response.setYes(true);
		assertTrue(response.getYes());
		response.setYes(false);
		assertFalse(response.getYes());
	}
}
