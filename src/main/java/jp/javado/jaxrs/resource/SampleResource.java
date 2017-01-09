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
		System.out.println("okokok");
		return Response.status(Response.Status.OK).entity("say").build();
	}


	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sample getSample(@PathParam("id") Integer id) {
		Sample sample = new Sample();
		sample.setId(id);
		sample.setName("javado_jaxrs");
		return sample;
	}

	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response postSample(Sample sample) {
		System.out.println("-------post----------");
		System.out.println("sample id  : " + sample.getId());
		System.out.println("sample name: " + sample.getName());
		System.out.println("-----------------");
		
		return Response.ok().build();
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response putSample(Sample sample) {
		System.out.println("-------put----------");
		System.out.println("sample id  : " + sample.getId());
		System.out.println("sample name: " + sample.getName());
		System.out.println("-----------------");
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteSample(@PathParam("id") Integer id) {
		System.out.println("------delete------");
		System.out.println("target sample [id]=" + id);
		System.out.println("------------------");

		// statusに4xxや5xxを設定してリクエストに対してHTTPのプロトコルを使い、サーバの処理状態（リクエスト不正やサーバ処理エラーなど）を通知することができる
		int status = 200;
		return Response.status(status).build();
	}
}
