package jp.javado.jaxrs.exception;

import jp.javado.jaxrs.pojo.ErrorMessage;

import javax.ws.rs.core.Response;

/**
 * Created by haruki on 2016/12/18.
 */
public enum ErrorCase {

    // 400系
    CLIENT_PRODUCTID_MISS("JDO0001E", "存在しないproductIdが指定されました。", Response.Status.BAD_REQUEST),
    CLIENT_UPLOAD_FILE_SIZE_ERROR("JDO0002E", "ファイルサイズ容量:1MBを超えたファイルはアップロードできません。", Response.Status.BAD_REQUEST),
    CLIENT_FORBIDDEN_IPADDRESS("JDO0003E", "このAPIへのアクセスは許可されていません。", Response.Status.FORBIDDEN),
    CLIENT_UPLOAD_FILE_NAME_MISS("JDO0004E", "ファイル名が設定されていないため、アップロードできません。", Response.Status.BAD_REQUEST),

    // 500系
    SERVER_UPLOAD_FILE_ERROR("JD1001E", "サーバでエラーが発生したため、ファイルのアップロード処理に失敗しました。", Response.Status.INTERNAL_SERVER_ERROR);

    private String code;
    private String message;
    private Response.Status status;

    private ErrorCase(String code, String message, Response.Status status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String getErrorCaseMessage() { return message; }

    public String getCode() { return code; }

    public Response.Status getStatus() { return status; }

    public ErrorMessage getErrorMessage() { return new ErrorMessage(this.code, this.message); }

}
