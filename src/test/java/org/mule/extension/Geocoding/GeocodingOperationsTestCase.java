package org.mule.extension.Geocoding;

import org.junit.Test;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.mule.runtime.api.event.Event;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GeocodingOperationsTestCase extends MuleArtifactFunctionalTestCase {
	@Override
	protected String getConfigFile() {
		return "test-mule-config.xml";
	}

	@Test
	public void executeSayHiOperation() throws Exception {
		Event cordsFlow = flowRunner("cordsFlow").run();
		String payloadValue = ((String) cordsFlow.getMessage().getPayload().getValue());
		assertThat(payloadValue, is("Longitude"));
	}
}
