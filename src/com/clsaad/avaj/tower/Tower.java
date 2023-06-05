package com.clsaad.avaj.tower;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.clsaad.avaj.Flyable;
import com.clsaad.avaj.Output;

public class Tower {
	private List<Flyable> observers = new CopyOnWriteArrayList<>();

	public void register(Flyable p_flyable) {
		observers.add(p_flyable);
		Output.out.printf("Tower says: %s registered to weather tower.\n", p_flyable);
	}

	public void unregister(Flyable p_flyable) {
		observers.remove(p_flyable);
		Output.out.printf("Tower says: %s unregistered from weather tower.\n", p_flyable);
	}

	protected void conditionChanged() {
		this.observers.forEach(Flyable::updateConditions);

		// This is equivalent to this:

		// for (var f : this.observers) {
		// f.updateConditions();
		// }
	}
}
