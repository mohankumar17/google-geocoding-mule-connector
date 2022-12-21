package org.mule.extension.Geocoding.internal;

import java.util.Optional;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public enum GeocodingError implements ErrorTypeDefinition<GeocodingError> {
	INVALID_PARAMETER, BAD_CREDENTIALS, INVALID_API_RESPONSE;

	private ErrorTypeDefinition<? extends Enum<?>> parent;

	GeocodingError(ErrorTypeDefinition<? extends Enum<?>> parent) {
		this.parent = parent;
	}

	GeocodingError() {
	}

	@Override
	public Optional<ErrorTypeDefinition<? extends Enum<?>>> getParent() {
		return Optional.ofNullable(parent);
	}
}
