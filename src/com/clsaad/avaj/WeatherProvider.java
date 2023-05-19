package com.clsaad.avaj;

import com.clsaad.avaj.aircraft.Coordinates;

public final class WeatherProvider {
	private static volatile WeatherProvider instance = null;

	private String weather[] = { "RAIN", "FOG", "SUN", "SNOW" };

	private WeatherProvider() {}

	public String getCurrentWeather(Coordinates p_coordinates) {
		// TODO
		return null;
	}

	public static WeatherProvider getInstance() {
		if (instance == null) {
			instance = new WeatherProvider();
		}

		return instance;
	}
}
