package org.mule.extension.Geocoding.internal;

import java.util.HashSet;
import java.util.Set;

import org.mule.runtime.extension.api.annotation.error.ErrorTypeProvider;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;

public class GeocodingErrorProvider implements ErrorTypeProvider {

	@Override
	public Set<ErrorTypeDefinition> getErrorTypes() {
		HashSet<ErrorTypeDefinition> errors = new HashSet<>();

		errors.add(GeocodingError.INVALID_PARAMETER);
		errors.add(GeocodingError.BAD_CREDENTIALS);
		errors.add(GeocodingError.INVALID_API_RESPONSE);

		return errors;
	}
}
