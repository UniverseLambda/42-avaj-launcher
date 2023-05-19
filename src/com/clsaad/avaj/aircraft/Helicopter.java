package com.clsaad.avaj.aircraft;

import com.clsaad.avaj.WeatherProvider;
import com.clsaad.avaj.aircraft.Coordinates.ForcedLandingException;

public class Helicopter extends Aircraft {
	public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	@Override
	public void updateConditions() {
		var conditions = WeatherProvider.getInstance().getCurrentWeather(this.coordinates);

		try {
			switch (conditions) {
				case "SUN" -> {
					System.out.println(this.toString() + ": Flap flap flap in the sun.");
					this.coordinates = this.coordinates.add(10, 0, 2);
				}
				case "RAIN" -> {
					System.out.println(this.toString() + ": They see me slashin, it's rainin'");
					this.coordinates = this.coordinates.add(5, 0, 0);
				}
				case "FOG" -> {
					System.out.println(this.toString() + ": The fog vision is based on the movement");
					this.coordinates = this.coordinates.add(1, 0, 0);
				}
				case "SNOW" -> {
					System.out.println(this.toString() + ": Okay. Radioactive snow is hard to handle. Let's go down a little");
					this.coordinates = this.coordinates.add(0, 0, -12);
				}
			}
		} catch (ForcedLandingException e) {
			this.weatherTower.unregister(this);
		}
	}

	@Override
	public String toString() {
		return "Helicopter" + super.toString();
	}
}
