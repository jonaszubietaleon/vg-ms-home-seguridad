package pe.edu.vallegrande.vg_ms_casas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HomeNotFoundException.class)
    public Mono<Void> handleFoodNotFoundException(ServerWebExchange exchange, HomeNotFoundException ex) {
        exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
        return exchange.getResponse().setComplete();
    }

    @ExceptionHandler(HomeInactiveException.class)
    public Mono<Void> handleFoodInactiveException(ServerWebExchange exchange, HomeInactiveException ex) {
        exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
        return exchange.getResponse().setComplete();
    }
}
