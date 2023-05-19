package com.clsaad.avaj;

import com.clsaad.avaj.tower.WeatherTower;

public abstract class Flyable {
	protected WeatherTower weatherTower;

	public abstract void updateConditions();

	public void registerTower(WeatherTower p_tower) {
		this.weatherTower.unregister(this);
		this.weatherTower = p_tower;
		this.weatherTower.register(this);
	}
}
