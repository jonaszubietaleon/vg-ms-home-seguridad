package pe.edu.vallegrande.vg_ms_casas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.vallegrande.vg_ms_casas.model.Home;
import pe.edu.vallegrande.vg_ms_casas.repository.HomeRepository;
import pe.edu.vallegrande.vg_ms_casas.service.HomeService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HomeServiceTest {

    @Mock
    private HomeRepository homeRepository;

    @InjectMocks
    private HomeService homeService;

    @Test
    void testFindAll() {
        Home h1 = new Home(1, "Casa A", "Dirección A", "A");
        Home h2 = new Home(2, "Casa B", "Dirección B", "A");

        when(homeRepository.findAll()).thenReturn(Flux.just(h1, h2));

        StepVerifier.create(homeService.findAll())
                .expectNext(h1)
                .expectNext(h2)
                .verifyComplete();
    }

    @Test
    void testFindById() {
        Home home = new Home(1, "Casa A", "Dirección A", "A");
        when(homeRepository.findById(1)).thenReturn(Mono.just(home));

        StepVerifier.create(homeService.findById(1))
                .expectNext(home)
                .verifyComplete();
    }

    @Test
    void testSave() {
        Home home = new Home(null, "Nueva Casa", "Dirección Nueva", "A");
        Home savedHome = new Home(1, "Nueva Casa", "Dirección Nueva", "A");

        when(homeRepository.save(home)).thenReturn(Mono.just(savedHome));

        StepVerifier.create(homeService.save(home))
                .expectNext(savedHome)
                .verifyComplete();
    }

    @Test
    void testUpdate() {
        Home existing = new Home(1, "Casa A", "Dirección A", "A");
        Home updates = new Home(null, "Casa Actualizada", "Dirección Actualizada", "A");
        Home updated = new Home(1, "Casa Actualizada", "Dirección Actualizada", "A");

        when(homeRepository.findById(1)).thenReturn(Mono.just(existing));
        when(homeRepository.save(existing)).thenReturn(Mono.just(updated));

        StepVerifier.create(homeService.update(1, updates))
                .expectNext(updated)
                .verifyComplete();
    }

    @Test
    void testDelete() {
        Home home = new Home(1, "Casa A", "Dirección A", "A");
        Home inactiva = new Home(1, "Casa A", "Dirección A", "I");

        when(homeRepository.findById(1)).thenReturn(Mono.just(home));
        when(homeRepository.save(home)).thenReturn(Mono.just(inactiva));

        StepVerifier.create(homeService.delete(1))
                .verifyComplete();
    }

    @Test
    void testRestore() {
        Home home = new Home(1, "Casa A", "Dirección A", "I");
        Home restaurada = new Home(1, "Casa A", "Dirección A", "A");

        when(homeRepository.findById(1)).thenReturn(Mono.just(home));
        when(homeRepository.save(home)).thenReturn(Mono.just(restaurada));

        StepVerifier.create(homeService.restore(1))
                .expectNext(restaurada)
                .verifyComplete();
    }

    @Test
    void testFindActive() {
        Home h1 = new Home(1, "Casa A", "Dir A", "A");
        when(homeRepository.findByStatus("A")).thenReturn(Flux.just(h1));

        StepVerifier.create(homeService.findActive())
                .expectNext(h1)
                .verifyComplete();
    }


@Test
void testFindInactive() {
    Home h1 = new Home(1, "Casa B", "Dir B", "I");
    when(homeRepository.findByStatus("I")).thenReturn(Flux.just(h1));

    StepVerifier.create(homeService.findInactive())
            .expectNext(h1)
            .verifyComplete();
}
}

