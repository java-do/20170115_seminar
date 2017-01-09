package jp.javado.jaxrs.exception;

import jp.javado.jaxrs.pojo.ErrorMessage;

/**
 * Created by haruki on 2016/12/18.
 */
public enum ErrorCase {

    // 400系
    CLIENT_PRODUCTID_MISS("存在しないproductIdが指定されました。"),
    CLIENT_UPLOAD_FILE_SIZE_ERROR("ファイルサイズ容量:1MBを超えたファイルはアップロードできません。"),
    CLIENT_FORBIDDEN_IPADDRESS("このAPIへのアクセスは許可されていません。"),

    // 500系
    SERVER_UPLOAD_FILE_ERROR("サーバでエラーが発生したため、ファイルのアップロード処理に失敗しました。");

    private String message;

    private ErrorCase(String message) { this.message = message; }

    public String getErrorCaseMessage() { return message; }

    public ErrorMessage getErrorMessage() { return new ErrorMessage(this.message); }

}
