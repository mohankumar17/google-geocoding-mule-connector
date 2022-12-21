package org.mule.extension.Geocoding.internal;

public final class GeocodingConnection {

	private String apiKey;

	public GeocodingConnection(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void invalidate() {
		this.apiKey = null;
	}
}
