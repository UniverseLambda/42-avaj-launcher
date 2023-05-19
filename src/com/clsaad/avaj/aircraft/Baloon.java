package com.clsaad.avaj.aircraft;

import com.clsaad.avaj.WeatherProvider;
import com.clsaad.avaj.aircraft.Coordinates.ForcedLandingException;

public class Baloon extends Aircraft {
	public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	@Override
	public void updateConditions() {
		var conditions = WeatherProvider.getInstance().getCurrentWeather(this.coordinates);

		try {
			switch (conditions) {
				case "SUN" -> {
					System.out.println(this.toString() + ": Hmmmmm. SUN. Let's rise.");
					this.coordinates = this.coordinates.add(2, 0, 4);
				}
				case "RAIN" -> {
					System.out.println(this.toString() + ": Plop plop mother lover");
					this.coordinates = this.coordinates.add(0, 0, -5);
				}
				case "FOG" -> {
					System.out.println(this.toString() + ": You can't see me");
					this.coordinates = this.coordinates.add(0, 0, -3);
				}
				case "SNOW" -> {
					System.out.println(this.toString() + ": I wish I was a snow man...");
					this.coordinates = this.coordinates.add(0, 0, -15);
				}
			}
		} catch (ForcedLandingException e) {
			this.weatherTower.unregister(this);
		}
	}

	@Override
	public String toString() {
		return "Baloon" + super.toString();
	}
}
