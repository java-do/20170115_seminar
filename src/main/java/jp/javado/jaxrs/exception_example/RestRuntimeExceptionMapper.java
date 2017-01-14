package jp.javado.jaxrs.exception_example;

import jp.javado.jaxrs.exception.ErrorCase;
import jp.javado.jaxrs.exception.RestRuntimeException;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by haruki on 2017/01/09.
 */
@Provider
public class RestRuntimeExceptionMapper implements ExceptionMapper<RestRuntimeException> {

    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public Response toResponse(RestRuntimeException e) {
        e.printStackTrace();

        ErrorCase errorCase = e.getErrorCase();

        /*
        String message = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            message = mapper.writeValueAsString(errorCase.getErrorMessage());
        } catch (JsonProcessingException ee) {
            ee.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException ee) {
            ee.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        */

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorCase.getErrorMessage()).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
