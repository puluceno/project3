/**
 * 
 */
package edu.luc.comp433.service.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.http.MediaType;

/**
 * @author Bruno Correa <brunogmc at gmail>
 *
 */
@Path("/")
public class APIVersion {

	private static final String apiVersion = "bookStoreApi-v0.1";

	@GET
	@Produces({MediaType.TEXT_PLAIN_VALUE})
	public Response restfulVersion() {
		return Response.ok(apiVersion).build();
	}
}
