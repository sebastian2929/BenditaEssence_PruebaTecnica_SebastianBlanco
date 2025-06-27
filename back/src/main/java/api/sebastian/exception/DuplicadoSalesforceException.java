package api.sebastian.exception;

public class DuplicadoSalesforceException extends RuntimeException {
    public DuplicadoSalesforceException(String mensaje) {
        super(mensaje);
    }
}
