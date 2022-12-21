package org.mule.extension.Geocoding.internal;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class GeocodingParamater {
	@Parameter
	@DisplayName("Address")
	@Expression(ExpressionSupport.SUPPORTED)
	private String address;

	public String getAddress() {
		return address;
	}

}
