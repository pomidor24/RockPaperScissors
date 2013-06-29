package com.drapeko.rps.util;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList<E> {

	private static final long serialVersionUID = 1L;
	
	private final int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(E o) {
        super.add(o);
        while (size() > limit) { super.remove(); }
        return true;
    }
}