package com.clsaad.avaj.conf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

import com.clsaad.avaj.Flyable;
import com.clsaad.avaj.aircraft.Coordinates;
import com.clsaad.avaj.aircraft.factory.AircraftFactory;

public class FromFileGenerator {
	private FromFileGenerator() {
	}

	public static Conf generate(String path) {
		int lineIdx = 0;

		try (var lines = Files.newBufferedReader(Paths.get(path)).lines()) {
			Optional<Integer> iterCount = Optional.empty();
			var aircrafts = new ArrayList<Flyable>();

			// SAFETY(-ish): Only using it once, so we don't care about the re-iterability
			// requirement of Iterable
			for (var line : (Iterable<String>) lines::iterator) {
				++lineIdx;

				System.out.println("line: " + line);

				if (iterCount.isEmpty()) {
					iterCount = Optional.of(tryParseInteger(line, lineIdx));
					continue;
				}

				var components = line.split("[\\t ]+");

				var tmp_comps = new ArrayList<>(components.length);
				for (var comp : components) {
					if (!comp.isEmpty()) {
						tmp_comps.add(comp);
					}
				}

				components = tmp_comps.toArray(new String[0]);

				if (components.length != 5) {
					if (components.length < 5) {
						System.out.println("Not enough parameters at line " + lineIdx);
					} else {
						System.out.println("Too many parameters at line " + lineIdx);
					}
					return null;
				}

				var type = components[0];
				var name = components[1];
				var longitude = tryParseInteger(components[2], lineIdx);
				var latitude = tryParseInteger(components[3], lineIdx);
				var height = Math.min(tryParseInteger(components[4], lineIdx), 100);

				if (longitude < 0) {
					System.out.println("Invalid longitude value at line " + lineIdx + ": " + longitude);
					return null;
				}

				if (latitude < 0) {
					System.out.println("Invalid latitude value at line " + lineIdx + ": " + latitude);
					return null;
				}

				if (height < 0) {
					System.out.println("Invalid height value at line " + lineIdx + ": " + height);
					return null;
				}

				aircrafts.add(AircraftFactory.newAircraft(type, name, new Coordinates(longitude, latitude, height)));
			}

			if (iterCount.isEmpty()) {
				System.out.println("Empty scenario file");
				return null;
			}

			return new Conf(iterCount.get().intValue(), aircrafts.toArray(new Flyable[0]));
		} catch (IOException e) {
			System.out.println("Could not load configuration file: " + e);
		} catch (SilentException e) {
		} catch (Exception e) {
			System.out.println("Error at line " + lineIdx + ": " + e.getMessage());
		}

		// Default fallback
		return null;
	}

	private static int tryParseInteger(String str, int lineIdx) throws SilentException {
		try {
			return Integer.parseInt(str);

		} catch (Exception e) {
			System.out.println("Integer expected at line " + lineIdx);
		}
		throw new SilentException();
	}

	private static class SilentException extends Exception {
		public SilentException() {
		}
	}
}
