package jp.javado.jaxrs.filter;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * 全リソースに対するフィルター　（リクエストを受け付けた時）
 * Created by haruki on 2017/01/13.
 */
@Provider
public class RestRequestFilter implements ContainerRequestFilter {

    private static final String LIMITED_PATH = "product/limited";

    @Context
    private HttpServletRequest request;  // injection

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("[filter] absolutepath: " + containerRequestContext.getUriInfo().getAbsolutePath()); // 絶対パス
        System.out.println("[filter] path: " + containerRequestContext.getUriInfo().getPath()); // Resourceクラスで定義したパス

        // ここのResourceで定義せずにここでパス指定でIPADDRESS制限ができる
        System.out.println("[filter] ipaddress: " +request.getRemoteAddr()); //IPv6
        if ( LIMITED_PATH.equals(containerRequestContext.getUriInfo().getPath()) ) {
            String ipaddress = request.getRemoteAddr();
            System.out.println("[filter] ipaddress: " + ipaddress);
        }
    }
}
