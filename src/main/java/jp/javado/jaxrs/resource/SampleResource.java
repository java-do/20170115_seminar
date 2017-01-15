package jp.javado.jaxrs.resource;

import jp.javado.jaxrs.pojo.Sample;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample")
public class SampleResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response get() {
		System.out.println("Rest request OK!");
		return Response.status(Response.Status.OK).entity("Rest request OK!").build();
	}


}
