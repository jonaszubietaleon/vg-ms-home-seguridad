package pe.edu.vallegrande.vg_ms_casas.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface HomeRepository extends ReactiveCrudRepository<Home, Integer> {
    Flux<Home> findByStatus(String status);
}
