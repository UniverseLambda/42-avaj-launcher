package com.clsaad.avaj.aircraft;

import com.clsaad.avaj.Output;
import com.clsaad.avaj.aircraft.Coordinates.ForcedLandingException;

public class JetPlane extends Aircraft {
	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	@Override
	public void updateConditions() {
		var conditions = this.weatherTower.getWeather(this.coordinates);

		try {
			switch (conditions) {
				case "SUN" -> {
					Output.out.println(toString() + ": Niiiiiiioooooom. Sunny sun.");
					this.coordinates = this.coordinates.add(0, 10, 2);
				}
				case "RAIN" -> {
					Output.out.println(toString() + ": Niiiiiiiioooooom. Bloub bloub water.");
					this.coordinates = this.coordinates.add(0, 5, 0);
				}
				case "FOG" -> {
					Output.out.println(toString() + ": niiiooom. Big plane is scared");
					this.coordinates = this.coordinates.add(0, 1, 0);
				}
				case "SNOW" -> {
					Output.out.println(toString() + ": Not niiiiioooom. Snow is bad.");
					this.coordinates = this.coordinates.add(0, 0, -7);
				}
			}
		} catch (ForcedLandingException e) {
			Output.out.println(toString() + " landing");
			this.weatherTower.unregister(this);
		}
	}

	@Override
	public String toString() {
		return "JetPlane" + super.toString();
	}
}
