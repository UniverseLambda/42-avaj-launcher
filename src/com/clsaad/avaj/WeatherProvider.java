package com.clsaad.avaj;

import java.util.HashMap;
import java.util.Random;

import com.clsaad.avaj.aircraft.Coordinates;

public final class WeatherProvider {
	private static volatile WeatherProvider instance = null;

	private String weather[] = { "RAIN", "FOG", "SUN", "SNOW" };
	private HashMap<Coordinates, String> weatherMap = new HashMap<>();

	private WeatherProvider() {
	}

	public String getCurrentWeather(Coordinates p_coordinates) {
		return this.weatherMap.computeIfAbsent(p_coordinates, (_c) -> {
			return weather[new Random().nextInt(weather.length)];
		});
	}

	public static WeatherProvider getInstance() {
		if (instance == null) {
			instance = new WeatherProvider();
		}

		return instance;
	}
}
