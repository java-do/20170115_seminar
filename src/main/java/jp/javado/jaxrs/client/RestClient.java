package jp.javado.jaxrs.client;

import jp.javado.jaxrs.pojo.Product;
import jp.javado.jaxrs.pojo.ProductList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by haruki on 2017/01/13.
 */
public class RestClient {

    public static void main(String... args) {
        getProduct();
        geteProductListPath();
        getProductList();
    }

    private static Product getProduct() {
        Product product = ClientBuilder.newClient()
                .target("http://localhost:8080")
                .path("rest/api/product/1")
                .request()
                .get(Product.class);

        System.out.println(product);
        return product;
    }

    private static List<Product> geteProductListPath() {
        Response productListResponse = ClientBuilder.newClient()
                .target("http://localhost:8080")
                .path("rest/api/product/list")
                .request()
                .get();
        System.out.println(productListResponse.getEntity());
        return null;
    }

    private static ProductList getProductList() {
        ProductList productList = ClientBuilder.newClient()
                .target("http://localhost:8080")
                .path("rest/api/product")
                .request()
                .get(ProductList.class);
        System.out.println(productList);
        return productList;
    }

}
