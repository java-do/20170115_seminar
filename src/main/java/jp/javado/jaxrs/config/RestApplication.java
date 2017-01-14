package jp.javado.jaxrs.config;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by haruki on 2017/01/09.
 */
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        System.out.println("resource config load.");
        //packages("jp.javado.jaxrs.resourceexample;jp.javado.jaxrs.exception;jp.javado.jaxrs.filter");
        packages("jp.javado.jaxrs.resource;jp.javado.jaxrs.exception;jp.javado.jaxrs.filter");
        register(org.glassfish.jersey.jackson.JacksonFeature.class);
        register(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
        register(org.glassfish.jersey.media.multipart.MultiPartFeature.class); // Multipartを動かすために必要
        //property(ServerProperties.PROVIDER_SCANNING_RECURSIVE, false);
    }
}
