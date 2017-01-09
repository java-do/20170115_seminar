package jp.javado.jaxrs.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public Response toResponse(RestRuntimeException e) {
        e.printStackTrace();

        ErrorCase errorCase = e.getErrorCase();

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

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
