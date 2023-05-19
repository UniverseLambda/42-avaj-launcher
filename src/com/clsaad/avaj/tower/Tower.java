package com.clsaad.avaj.tower;

import java.util.ArrayList;
import java.util.List;

import com.clsaad.avaj.Flyable;

public class Tower {
	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable p_flyable) {
		observers.add(p_flyable);
		System.out.printf("Tower says: %s registered to weather tower.\n", p_flyable);
	}

	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
		System.out.printf("Tower says: %s unregistered from weather tower.\n", p_flyable);
	}

	protected void conditionChanged() {
		// TODO
	}
}
