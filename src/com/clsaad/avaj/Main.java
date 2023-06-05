package com.clsaad.avaj;

import java.io.IOException;

import com.clsaad.avaj.conf.RandomGenerator;
import com.clsaad.avaj.tower.WeatherTower;

public class Main {
	public static void main(String[] args) {
		var conf = RandomGenerator.generate();

		try (var outHolder = new Output()) {
			var tower = new WeatherTower();

			for (var aircraft : conf.aircrafts()) {
				aircraft.registerTower(tower);
			}

			for (int i = 0; i < conf.iteration_count(); ++i) {
				tower.changeWeather();
			}

		} catch (IOException e) {
			System.err.println("IO exception: " + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Exception caught: " + e);
			e.printStackTrace();
		}
	}
}
