package com.drapeko.rps.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestLimitedQueue {

	@Test
	public void testLimitedQueue() {
		LimitedQueue<Integer> queue = new LimitedQueue<Integer>(3);
		queue.add(1);
		assertEquals(1, queue.size());
		queue.add(2);
		assertEquals(2, queue.size());
		queue.add(3);
		assertEquals(3, queue.size());
		queue.add(4);
		assertEquals(3, queue.size());
		
		assertEquals(2, (int)queue.getFirst());
		assertEquals(4, (int)queue.getLast());
	}
}
