package jp.javado.jaxrs.resource_example;

import jp.javado.jaxrs.exception.RestException;
import jp.javado.jaxrs.exception.RestRuntimeException;
import jp.javado.jaxrs.exception.ErrorCase;
import jp.javado.jaxrs.pojo.Product;
import jp.javado.db.ProductDatabase;
//import jp.javado.jaxrs.pojo.ProductList;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

//import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.util.List;

@Path("/product")
public class ExampleProductResource {

    // 各自の環境に合わせて変更すること
    private static final String FILE_SAVE_PATH = "/tmp/";

    // ファイル容量制限 初期値は1MBに設定. 変更してみてください
    private static final long FILE_UPLOAD_SIZE_MAX = 1 * 1024 * 1024;

    // http://xxx.xxx/rest/api/product/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product get(@PathParam("id") int id) throws RestException {
        log("/rest/api/product/" + id + "  を呼び出しました.");
        //if (true) throw new RestRuntimeException(ErrorCase.CLIENT_UPLOAD_FILE_SIZE_ERROR);

        Product product = ProductDatabase.select(id);

        // 異常系処理: DBから指定されたIDの商品が見つからない場合
        if (product == null) {
            // mapper使わない場合と比較して
            throw new RestException(ErrorCase.CLIENT_PRODUCTID_MISS);
        }

        // 正常系: DBに指定されたIDの商品が見つかった場合
        return product;
    }

    // http://xxx.xxx/rest/api/product/limited
/*
    @GET
    @Path("/limited")
    @Produces(MediaType.APPLICATION_JSON)
    public Product ipaddressLimit(@Context HttpServletRequest request) throws RestException {
        log("/rest/api/product/limited" + "  を呼び出しました.");

        String ipaddress = request.getRemoteAddr(); // デフォルトはIPv6での取得、IPv4で取得したい場合はtomcatの起動で「-Djava.net.preferIPv4Stack=true」を設定する
        System.out.println("ipaddress: " + ipaddress);
        if ( "0:0:0:0:0:0:0:1".equals(ipaddress) ) {
            throw new RestException(ErrorCase.CLIENT_FORBIDDEN_IPADDRESS);
        }

        return new Product(9999, "北海道限定 焼きそば弁当", 120);
    }
*/
/*
    // 推奨しない
    // http://xxx.xxx/rest/api/product/list
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductListPath() {
        log("/rest/api/product/list   を呼び出しました.");
        List<Product> productList = ProductDatabase.selectAll();
        return productList;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductList getProductList() {
        log("/rest/api/product   を呼び出しました");
        List<Product> productList = ProductDatabase.selectAll();
        ProductList productList1 = new ProductList();
        productList1.setProductList(productList);
        return productList1;
    }
*/
    // http://xxx.xxx/rest/api/product/limited
    @POST
    @Path("/fileupload/multipart")
    @Consumes(MediaType.MULTIPART_FORM_DATA)  // multipart/form-data形式を処理するための指定
    public Response fileupload(@FormDataParam("fileUpload") InputStream inputStream,
                               @FormDataParam("fileUpload") FormDataContentDisposition content,
                               @FormDataParam("fileUpload") FormDataBodyPart bodyPart,
                               @HeaderParam("Content-Length") long contentLength)
            throws RestException {
        log("/rest/api/product/fileupload   を呼び出しました.");

        // ファイル名取得
        String filename = content.getFileName();
        log("filename=" + filename);

        // ファイルのContentType取得
        String filetype = bodyPart.getMediaType().toString();
        log("filetype=" + filetype);

        // Http RequestのContent-Length (multipart/form-dataで単一ファイルであればファイルサイズに近い値が取得可能)
        log("Content-Lenght=" + contentLength);

        try {
            // ファイル保存 保存先:FILE_SAVA_PATH/アップロードファイル名 (FILE_SAVE_PATHを各自の環境に変更すること. 初期設定は/tmp/(Mac用))
            Files.copy(inputStream, Paths.get(FILE_SAVE_PATH, filename), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RestRuntimeException(ErrorCase.SERVER_UPLOAD_FILE_ERROR);
        }

        //ProductDatabase.insert();

        // HTTP Status Code : 「201 created」 を指定して返却する. レスポンスボディは空. 何かjsonを返すならentityメソッドを使う
        return Response.status(Response.Status.CREATED).build();
    }

    // 共通処理としてかける場所はどこか
    // クライアントのmainコードでファイルアップロードはしてもしなくても
    // deleteを書いてみましょうとかね
    // errormessageを増やしてみましょうとか

    // http://xxx.xxx/rest/api/product/fileupload/media
    // @Consumesは設定しない Content-Typeをいろいろなものを受け付けるため
    // ファイル名を受け取るために, HTTPHeaderを拡張し, 独自定義したX-JavaDo-File-Nameを取得するようにした.　(慣例的に独自拡張はX-で始める)
    @POST
    @Path("/files")
    public Response fileuploadSimple(@QueryParam("uploadType") String uploadType,
                                     @HeaderParam("Content-Type") String contentType,
                                     @HeaderParam("Content-Length") long contentLength,
                                     @HeaderParam("Authorization") String authorization,
                                     @HeaderParam("X-JavaDo-File-Name") String fileName,
                                     InputStream inputStream) throws RestException {

        log("Content-Type=" + contentType);  // ファイルタイプ  image/jpgとかが来る
        log("Content-Length=" + contentLength);  // ファイルサイズ
        log("FileName=" + fileName);  // ファイル名

        // ファイルサイズチェック
        if ( contentLength > FILE_UPLOAD_SIZE_MAX ) {
            throw new RestException(ErrorCase.CLIENT_UPLOAD_FILE_SIZE_ERROR);
        }

        // ファイル名チェック
        if ( fileName == null || "".equals(fileName) ) {
            throw new RestException(ErrorCase.CLIENT_UPLOAD_FILE_NAME_MISS);
        }

        try {
            Files.copy(inputStream, Paths.get(FILE_SAVE_PATH, fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RestRuntimeException(ErrorCase.SERVER_UPLOAD_FILE_ERROR);
        }

        //Product product = new Product(100, "new product", 100);
        //return Response.status(Response.Status.CREATED).entity(product).build();

        // HTTP Status Code :「201 created」を指定して返却する. レスポンスボディは空. 何かjsonを返すならentityメソッドを使う
        return Response.status(Response.Status.CREATED).build();
    }

    private void log(Object log) {
        System.out.println(log);
    }
}
