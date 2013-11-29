package br.com.codate.cadrural.produtor.exc;

public class CpfCnpjJaExisteException extends Exception {

    private static final long serialVersionUID = 1L;

    public CpfCnpjJaExisteException() {
	super();
    }

    public CpfCnpjJaExisteException(String message, Throwable cause) {
	super(message, cause);
    }

    public CpfCnpjJaExisteException(String message) {
	super(message);
    }

    public CpfCnpjJaExisteException(Throwable cause) {
	super(cause);
    }

}
