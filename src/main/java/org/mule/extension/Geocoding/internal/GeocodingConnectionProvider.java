package org.mule.extension.Geocoding.internal;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeocodingConnectionProvider implements PoolingConnectionProvider<GeocodingConnection> {

	private final Logger LOGGER = LoggerFactory.getLogger(GeocodingConnectionProvider.class);

	@DisplayName("API Key")
	@Parameter
	private String apiKey;

	@Override
	public GeocodingConnection connect() throws ConnectionException {
		GeocodingConnection connection;
		try {
			connection = new GeocodingConnection(apiKey);
		} catch (Exception e) {
			throw new ConnectionException("Error occurred trying to connect.", e);
		}
		return connection;

	}

	@Override
	public void disconnect(GeocodingConnection connection) {
		try {
			connection.invalidate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public ConnectionValidationResult validate(GeocodingConnection connection) {
		return ConnectionValidationResult.success();
	}
}
