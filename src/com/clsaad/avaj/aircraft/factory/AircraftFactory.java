package com.clsaad.avaj.aircraft.factory;

import java.util.concurrent.atomic.AtomicLong;

import com.clsaad.avaj.Flyable;
import com.clsaad.avaj.aircraft.Baloon;
import com.clsaad.avaj.aircraft.Coordinates;
import com.clsaad.avaj.aircraft.Helicopter;
import com.clsaad.avaj.aircraft.JetPlane;

public final class AircraftFactory {
	private static AtomicLong idIncrement = new AtomicLong(0);

	public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
		var id = idIncrement.getAndIncrement();

		return switch (p_type.toLowerCase()) {
			case "baloon" -> new Baloon(id, p_name, p_coordinates);
			case "jetplane" -> new JetPlane(id, p_name, p_coordinates);
			case "helicopter" -> new Helicopter(id, p_name, p_coordinates);
			default -> throw new IllegalArgumentException("Invalid Aircraft type: " + p_name);
		};
	}
}