package com.clsaad.avaj.aircraft;

import java.util.Objects;

import com.clsaad.avaj.Flyable;
import com.clsaad.avaj.Output;
import com.clsaad.avaj.aircraft.Coordinates.ForcedLandingException;

public class Aircraft extends Flyable {
	protected long id;
	protected String name;
	protected Coordinates coordinates;

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
		Objects.requireNonNull(p_name, "Aircraft::name");
		Objects.requireNonNull(p_coordinates, "Aircraft::coordinates");

		this.id = p_id;
		this.name = p_name;
		this.coordinates = p_coordinates;
	}

	@Override
	public void updateConditions() {
		var conditions = this.weatherTower.getWeather(this.coordinates);

		try {
			switch (conditions) {
				case "SUN" -> {
					Output.out.println("what" + this.toString() + ": It's sunny, but who am I?");
					this.coordinates = this.coordinates.add(10, 0, 2);
				}
				case "RAIN" -> {
					Output.out.println("what" + this.toString() + ": It's raining, but who am I?");
					this.coordinates = this.coordinates.add(5, 0, 0);
				}
				case "FOG" -> {
					Output.out.println("what" + this.toString() + ": It's foggy, but who are we?");
					this.coordinates = this.coordinates.add(1, 0, 0);
				}
				case "SNOW" -> {
					Output.out.println("what" +
							this.toString() + ": Oh lord. The snow... IT'S COMING");
					this.coordinates = this.coordinates.add(0, 0, -12);
				}
			}
		} catch (ForcedLandingException e) {
			Output.out.println(toString() + " landing");
			this.weatherTower.unregister(this);
		}
	}

	@Override
	public String toString() {
		return "#" + name + '(' + id + ')';
	}
}