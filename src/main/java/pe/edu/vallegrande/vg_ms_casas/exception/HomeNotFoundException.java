package pe.edu.vallegrande.vg_ms_casas.exception;

public class HomeNotFoundException extends RuntimeException {
    public HomeNotFoundException(String message) {
        super(message);
    }
}