package jp.javado.jaxrs.resource;

import jp.javado.db.ProductDatabase;
import jp.javado.jaxrs.exception.ErrorCase;
import jp.javado.jaxrs.exception.RestException;
import jp.javado.jaxrs.pojo.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by haruki on 2017/01/14.
 */
@Path("/product")
public class ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(){
        Product product = new Product();
        product.setName("JavaDo");
        return product;
    }

    // http://xxx.xxx/rest/api/product/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product get(@PathParam("id") int id) throws RestException {
        log("/rest/api/product/" + id + "  を呼び出しました.");

        Product product = ProductDatabase.select(id);

        return product;
    }

    private void log(Object log) {
        System.out.println(log);
    }
}
