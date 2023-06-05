package com.clsaad.avaj.conf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import com.clsaad.avaj.Flyable;
import com.clsaad.avaj.aircraft.Aircraft;
import com.clsaad.avaj.aircraft.Coordinates;
import com.clsaad.avaj.aircraft.factory.AircraftFactory;

public class FromFileGenerator {
	private FromFileGenerator() {
	}

	public static Conf generate(String path) {
		try (var lines = Files.newBufferedReader(Paths.get(path)).lines()) {
			Optional<Integer> iterCount = Optional.empty();
			var aircrafts = new ArrayList<Flyable>();

			int lineIdx = 0;

			// SAFETY(-ish): Only using it once, so we don't care about the re-iterability
			// requirement of Iterable
			for (var line : (Iterable<String>) lines::iterator) {
				++lineIdx;

				if (iterCount.isEmpty()) {
					iterCount = Optional.of(tryParseInteger(line, lineIdx));
					continue;
				}

				var components = line.split("[\\t ]");

				if (components.length != 5) {
					if (components.length < 5) {
						System.err.println("Not enough parameters at line " + lineIdx);
					} else {
						System.err.println("Too many parameters at line " + lineIdx);
					}
					return null;
				}

				var type = components[0];
				var name = components[1];
				var longitude = tryParseInteger(components[2], lineIdx);
				var latitude = tryParseInteger(components[3], lineIdx);
				var height = tryParseInteger(components[4], lineIdx);

				if (longitude < 0) {
					System.err.println("Invalid longitude value at line " + lineIdx + ": " + longitude);
					return null;
				}

				if (latitude < 0) {
					System.err.println("Invalid latitude value at line " + lineIdx + ": " + latitude);
					return null;
				}

				if (height < 0 || height > 100) {
					System.err.println("Invalid height value at line " + lineIdx + ": " + height);
					return null;
				}

				aircrafts.add(AircraftFactory.newAircraft(type, name, new Coordinates(longitude, latitude, height)));
			}

			if (iterCount.isEmpty()) {
				System.err.println("Empty scenario file");
				return null;
			}

			return new Conf(iterCount.get().intValue(), aircrafts.toArray(new Flyable[0]));
		} catch (IOException e) {
			System.err.println("Could not load configuration file: " + e);
		} catch (SilentException e) {
		}

		// Default fallback
		return null;
	}

	private static int tryParseInteger(String str, int lineIdx) throws SilentException {
		try {
			return Integer.parseInt(str);

		} catch (Exception e) {
			System.err.println("Integer expected at line " + lineIdx);
		}
		throw new SilentException();
	}

	private static class SilentException extends Exception {
		public SilentException() {
		}
	}
}
