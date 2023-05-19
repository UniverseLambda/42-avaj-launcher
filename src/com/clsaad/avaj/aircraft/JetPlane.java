package com.clsaad.avaj.aircraft;

import com.clsaad.avaj.WeatherProvider;
import com.clsaad.avaj.aircraft.Coordinates.ForcedLandingException;

public class JetPlane extends Aircraft {
	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	@Override
	public void updateConditions() {
		var conditions = WeatherProvider.getInstance().getCurrentWeather(this.coordinates);

		try {
			switch (conditions) {
				case "SUN" -> {
					System.out.println(toString() + ": Niiiiiiioooooom. Sunny sun.");
					this.coordinates = this.coordinates.add(0, 10, 2);
				}
				case "RAIN" -> {
					System.out.println(toString() + ": Niiiiiiiioooooom. Bloub bloub water.");
					this.coordinates = this.coordinates.add(0, 5, 0);
				}
				case "FOG" -> {
					System.out.println(toString() + ": niiiooom. Big plane is scared0");
					this.coordinates = this.coordinates.add(0, 1, 0);
				}
				case "SNOW" -> {
					System.out.println(toString() + ": Not niiiiioooom. Snow is bad.");
					this.coordinates = this.coordinates.add(0, 0, -7);
				}
			}
		} catch (ForcedLandingException e) {
			this.weatherTower.unregister(this);
		}
	}

	@Override
	public String toString() {
		return "JetPlane" + super.toString();
	}
}
