package com.clsaad.avaj.aircraft;

import com.clsaad.avaj.Flyable;

public class Aircraft extends Flyable {
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
		this.id = p_id;
		this.name = p_name;
		this.coordinates = p_coordinates;
	}

	@Override
	public void updateConditions() {
		// TODO
	}

	@Override
	public String toString() {
		return "#" + name + '(' + id + ')';
	}
}