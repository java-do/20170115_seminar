package jp.javado.jaxrs.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

/**
 * Created by haruki on 2017/01/09.
 */
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        System.out.println("resource config load.");
        packages("jp.javado.jaxrs.resource;jp.javado.jaxrs.exception");
        register(org.glassfish.jersey.jackson.JacksonFeature.class);
        register(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
        //property(ServerProperties.PROVIDER_SCANNING_RECURSIVE, false);
    }
}
