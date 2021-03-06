package jp.javado.jaxrs.exception_example;

import jp.javado.jaxrs.exception.ErrorCase;
import jp.javado.jaxrs.exception.RestException;
import jp.javado.jaxrs.pojo.ErrorMessage;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by haruki on 2016/12/18.
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(RestException e) {

        ErrorCase errorCase = e.getErrorCaseMessage();

        ErrorMessage message   = errorCase.getErrorMessage();
        Response.Status status = errorCase.getStatus();

        return Response.status(status).entity(message).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}
