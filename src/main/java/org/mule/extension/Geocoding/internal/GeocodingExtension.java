package org.mule.extension.Geocoding.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.error.ErrorTypes;

@Xml(prefix = "geocoding")
@Extension(name = "Geocoding")
@ErrorTypes(GeocodingError.class)
@Configurations(GeocodingConfiguration.class)
public class GeocodingExtension {

}
