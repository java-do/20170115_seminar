package jp.javado.exception;

public class JavaDoDBException extends RuntimeException {

	private static final long serialVersionUID = 45183552895877466L;

	public JavaDoDBException(Throwable e) {
		super("JavaDoDBの接続中にエラーが発生しました。", e);
	}
}
