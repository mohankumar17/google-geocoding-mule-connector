package org.mule.extension.Geocoding.internal;

import org.mule.runtime.extension.api.exception.ModuleException;

public final class APICallException extends ModuleException {

	public APICallException(Exception cause) {
		super(GeocodingError.INVALID_API_RESPONSE, cause);
	}
}
