package com.clsaad.avaj;

import java.io.IOException;

import com.clsaad.avaj.conf.FromFileGenerator;
import com.clsaad.avaj.tower.WeatherTower;

public class Main {
	public static void main(String[] args) {
		var conf = FromFileGenerator.generate(args[0]);

		if (conf == null) {
			// Error has already been printed. Silenty exit...
			System.exit(1);
		}

		System.out.println("Conf:");
		System.out.println("Iteration count: " + conf.iterationCount());
		System.out.println("Aircrafts: ");
		for (var aircraft : conf.aircrafts()) {
			System.out.printf("- %s\n", aircraft.toString());
		}

		try (var outHolder = new Output()) {
			var tower = new WeatherTower();

			for (var aircraft : conf.aircrafts()) {
				aircraft.registerTower(tower);
			}

			for (int i = 0; i < conf.iterationCount(); ++i) {
				tower.changeWeather();
			}

		} catch (IOException e) {
			System.out.println("IO exception: " + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception caught: " + e);
			e.printStackTrace();
		}
	}
}
