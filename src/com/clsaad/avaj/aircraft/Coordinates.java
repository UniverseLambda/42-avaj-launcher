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

	@Override
	public int hashCode() {
		int mask = (this.longitude & 0xFF) | ((this.latitute >> 8) & 0xFF) | ((this.height >> 16) & 0xFF);

		return mask;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Coordinates c) {
			return this.hashCode() == obj.hashCode();
		}

		return super.equals(obj);
	}
}