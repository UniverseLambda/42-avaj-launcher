package com.clsaad.avaj.aircraft;

public class Coordinates {
	private int longitude;
	private int latitute;
	private int height;

	public Coordinates(int p_longitude, int p_latitute, int p_height) {
		this.longitude = p_longitude;
		this.latitute = p_latitute;
		this.height = p_height;
	}

	public int getLongitude() {
		return longitude;
	}

	public int getLatitute() {
		return latitute;
	}

	public int getHeight() {
		return height;
	}

	public Coordinates add(int p_longitude, int p_latitute, int p_height) throws ForcedLandingException {
		var result = new Coordinates(this.longitude + p_longitude, this.latitute + p_latitute, this.height + p_height);

		if (result.longitude < 0) {
			System.out.println(
					"SIMULATION WARNING: computation of new coordinates would overflow longitude. Capping it at maximum value...");

			result.longitude = Integer.MAX_VALUE;
		}

		if (result.latitute < 0) {
			System.out.println(
					"SIMULATION WARNING: computation of new coordinates would overflow latitute. Capping it at maximum value...");

			result.latitute = Integer.MAX_VALUE;
		}

		if (result.height <= 0)
			throw new ForcedLandingException();
		if (result.height > 100)
			result.height = 100;

		return result;

	}

	public Coordinates add(Coordinates other) throws ForcedLandingException {
		return this.add(other.longitude, other.latitute, other.height);
	}

	public static class ForcedLandingException extends Exception {
		public ForcedLandingException() {
			super();
		}
	}

	// public static class WouldOverflowException {

	// }

	@Override
	public int hashCode() {
		int mask = (this.longitude & 0xFF) | ((this.latitute & 0xFF) << 8) | ((this.height & 0xFF) << 16);

		return mask;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Coordinates c) {
			return this.longitude == c.longitude && this.latitute == c.latitute && this.height == c.height;
		}

		return super.equals(obj);
	}
}