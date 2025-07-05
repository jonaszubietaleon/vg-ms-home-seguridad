package pe.edu.vallegrande.vg_ms_casas.webclient;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import pe.edu.vallegrande.vg_ms_casas.dto.ProductDTO;

@Service
public class ClientConsumption {

    private final WebClient webClient;

    public ClientConsumption() {
        this.webClient = WebClient.builder()
                .baseUrl("https://ms-product-ix0t.onrender.com/NPH/products")
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Flux<ProductDTO> consumeAllProducts() {
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductDTO.class);
    }
}
