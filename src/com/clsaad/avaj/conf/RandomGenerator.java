package com.clsaad.avaj.conf;

import java.util.Random;

import com.clsaad.avaj.Flyable;
import com.clsaad.avaj.aircraft.factory.AircraftFactory;

public class RandomGenerator {
	public static Conf generate() {
		var rand = new Random();

		var iter = rand.nextInt(10, Integer.MAX_VALUE - 10);
		var maxAircrafts = rand.nextInt(50);

		Flyable[] aircrafts = new Flyable[maxAircrafts];
		char[] chars = {'B', 'H', 'J'};
		String[] type = {"Baloon", "Helicopter", "JetPlane"};
		int[] count = {0, 0, 0};

		for (int i = 0; i < aircrafts.length; ++i) {
			var selected = rand.nextInt(3);

			aircrafts[i] = AircraftFactory.newAircraft(type[selected], makeName(chars[selected], count[selected]++), null);
		}

		return new Conf(iter, aircrafts);
	}

	private static String makeName(char c, int count) {
		return "" + c + count;
	}
}
