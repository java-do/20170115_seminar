package jp.javado.jaxrs.pojo;

import sun.plugin2.util.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haruki on 2017/01/13.
 */
public class ProductList {

    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Product p : productList) {
            sb.append(p.toString() + "\r\n");
        }

        return sb.toString();
    }
}
