package org.mule.extension.Geocoding.internal;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;

public class GeocodingOperations {

	private final static Logger LOGGER = LoggerFactory.getLogger(GeocodingOperations.class);

	private static Client client;
	static {
		client = ClientBuilder.newClient().property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
	}

	@Throws(GeocodingErrorProvider.class)
	@MediaType(value = MediaType.ANY, strict = false)
	public Result<String, Void> getCoordinates(@Config GeocodingConfiguration config,
			@ParameterGroup(name = "Address Paramater") GeocodingParamater param,
			@Connection GeocodingConnection connection) {

		String apiKey = connection.getApiKey();
		String url = config.getUrl();
		String address = param.getAddress();

		JSONObject cordJson = null;
		try {
			cordJson = apiCall(apiKey, url, address);
		} catch (Exception e) {
			throw new ModuleException(GeocodingError.INVALID_API_RESPONSE, e);
		}

		return Result.<String, Void>builder().output(cordJson.toString()).build();
	}

	private static JSONObject apiCall(String apiKey, String url, String address) throws APICallException {
		JSONObject cordJson = new JSONObject();

		WebTarget webTarget = client.target(url);
		Response res = webTarget.queryParam("address", address).queryParam("key", apiKey)
				.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(Response.class);

		String stringResp = res.readEntity(String.class);
		JSONObject jsonResp = null;

		try {
			jsonResp = new JSONObject(stringResp);
			if (!jsonResp.get("status").equals("OK")) {
				LOGGER.error("Invalid Response");
				throw new APICallException(new Exception(jsonResp.get("status").toString()));

			} else {
				if (jsonResp.has("results")) {
					JSONArray results = jsonResp.getJSONArray("results");
					JSONObject cords = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

					cordJson.put("Latitude", cords.get("lat"));
					cordJson.put("Longitude", cords.get("lng"));

					LOGGER.info("Co-ordinates retreived");
				}
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new APICallException(e);
		}

		return cordJson;
	}

}
