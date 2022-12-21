package org.mule.extension.Geocoding.internal;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

@Operations(GeocodingOperations.class)
@ConnectionProviders(GeocodingConnectionProvider.class)
public class GeocodingConfiguration {

	@Parameter
	@DisplayName("Geocode URL")
	@Placement(order = 1)
	@Expression(ExpressionSupport.SUPPORTED)
	@Optional(defaultValue = "https://maps.googleapis.com/maps/api/geocode/json")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
