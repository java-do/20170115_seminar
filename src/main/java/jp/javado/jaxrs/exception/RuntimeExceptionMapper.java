package jp.javado.jaxrs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by haruki on 2016/12/18.
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        System.out.println("RuntimeExceptionMapper execute");
        e.printStackTrace();
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }
}
