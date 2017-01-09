package jp.javado.jaxrs.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public Response toResponse(RestException e) {

        ErrorCase errorCase = e.getErrorCaseMessage();

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

        return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}
