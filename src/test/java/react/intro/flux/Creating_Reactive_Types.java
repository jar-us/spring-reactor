package react.intro.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Creating_Reactive_Types {


    @Test
    public void create_flux_from_interval() {
        Flux<Long> integerFlux = Flux.interval(Duration.ofSeconds(1)).take(5);

//        integerFlux.subscribe(System.out::println);
        StepVerifier.create(integerFlux)
                .expectNext(0l, 1l, 2l, 3l, 4l)
                .verifyComplete();
    }

    @Test
    public void create_flux_from_range() {
        Flux<Integer> integerFlux = Flux.range(2, 5);

        StepVerifier.create(integerFlux)
//                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                .expectNext(6)
                .verifyComplete();

    }

    @Test
    public void create_flux_from_stream() {
        Stream<String> fruitArray = Stream.of("Mango", "Apple", "Grapes", "Banana");
        Flux<String> fluxFromFruitArray = Flux.fromStream(fruitArray);

        StepVerifier.create(fluxFromFruitArray)
                .expectNext("Mango", "Apple", "Grapes", "Banana")
                .verifyComplete();
    }

    @Test
    public void create_flux_from_arrayList() {
        List<String> fruitArray = Arrays.asList("Mango", "Apple", "Grapes", "Banana");
        Flux<String> fluxFromFruitArray = Flux.fromIterable(fruitArray);

        StepVerifier.create(fluxFromFruitArray)
                .expectNext("Mango", "Apple", "Grapes", "Banana")
                .verifyComplete();
    }

    @Test
    public void create_flux_from_array() {
        String[] fruitArray = {"Mango", "Apple", "Grapes", "Banana"};
        Flux<String> fluxFromFruitArray = Flux.fromArray(fruitArray);

        StepVerifier.create(fluxFromFruitArray)
                .expectNext("Mango", "Apple", "Grapes", "Banana")
                .verifyComplete();
    }


    @Test
    public void create_flux_from_objects_and_print() {
        Flux<String> fruitsFlux = Flux.just("Mango", "Apple", "Grapes", "Banana");
        fruitsFlux.subscribe(System.out::println);
    }


    @Test
    public void create_flux_from_objects_and_assert() {
        Flux<String> fruitsFlux = Flux.just("Mango", "Apple", "Grapes", "Banana");

        StepVerifier.create(fruitsFlux)
                .expectNext("Mango")
                .expectNext("Apple")
                .expectNext("Grapes")
                .expectNext("Banana")
                .verifyComplete();


        StepVerifier.create(fruitsFlux)
                .expectNext("Mango", "Apple", "Grapes", "Banana")
                .verifyComplete();
    }

}
