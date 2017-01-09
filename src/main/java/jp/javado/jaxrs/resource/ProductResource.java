package jp.javado.jaxrs.resource;

import jp.javado.jaxrs.exception.RestException;
import jp.javado.jaxrs.exception.RestRuntimeException;
import jp.javado.jaxrs.exception.ErrorCase;
import jp.javado.jaxrs.pojo.Product;
import jp.javado.db.ProductDatabase;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/product")
public class ProductResource {

    private static final String FILE_SAVE_PATH = "/users/uenoharuki/Desktop/";

    // ファイル容量制限 初期値は1MBに設定. 変更してみてください
    private static final long FILE_UPLOAD_SIZE_MAX = 1 * 1024 * 1024;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product get(@PathParam("id") int id) throws RestException {

        System.out.println("product get");

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

    @GET
    @Path("/limited/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product ipaddressLimit(@Context HttpServletRequest request, @PathParam("id") int id) throws RestException {
        System.out.println("ip address limited get");

        String ipaddress = request.getRemoteAddr(); // デフォルトはIPv6での取得、IPv4で取得したい場合はtomcatの起動で「-Djava.net.preferIPv4Stack=true」を設定する
        System.out.println("ipaddress: " + ipaddress);
        if ( "0:0:0:0:0:0:0:1".equals(ipaddress) ) {
            throw new RestException(ErrorCase.CLIENT_FORBIDDEN_IPADDRESS);
        }

        return new Product(9999, "北海道限定 焼きそば弁当", 120);
    }
/*
    @POST
    @Path("/fileupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)  // multipart/form-data形式を処理するための指定
    public Response fileupload(@FormDataParam("file") InputStream inputStream,
                               @FormDataParam("file") FormDataContentDisposition content)
            throws RestException {

        String filename = content.getFileName(); // ファイル名
        long filesize = content.getSize();     // ファイルサイズ

        // ファイルサイズチェック
        if ( filesize > FILE_UPLOAD_SIZE_MAX ) {
            throw new RestException(ErrorMessage.CLIENT_UPLOAD_FILE_SIZE_ERROR);
        }

        try {
            Files.copy(inputStream, Paths.get(FILE_SAVE_PATH, filename), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RestRuntimeException(ErrorMessage.SERVER_UPLOAD_FILE_ERROR);
        }

        //ProductDatabase.insert();

        // HTTP Status Code : 「201 created」 を指定して返却する. レスポンスボディは空.
        return Response.status(Response.Status.CREATED).build();
    }

    // 共通処理としてかける場所はどこか
    // クライアントのmainコードでファイルアップロードはしてもしなくても
    // deleteを書いてみましょうとかね
    // errormessageを増やしてみましょうとか
*/

}
