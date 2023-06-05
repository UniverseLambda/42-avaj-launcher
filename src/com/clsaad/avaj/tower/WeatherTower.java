package com.clsaad.avaj.tower;

import com.clsaad.avaj.WeatherProvider;
import com.clsaad.avaj.aircraft.Coordinates;

public class WeatherTower extends Tower {
	public String getWeather(Coordinates p_coordinates) {
		return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
	}

	public void changeWeather() {
		this.conditionChanged();
	}
}
