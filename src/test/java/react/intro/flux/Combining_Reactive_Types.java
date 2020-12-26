package react.intro.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;

public class Combining_Reactive_Types {

    @Test
    public void flux_from_first_flux() {
        Flux<String> employeeFlux = Flux.just("Amit", "Sumit", "Ajeet").delaySubscription(Duration.ofMillis(250));

        Flux<String> profession = Flux.just("Electrical Engineer", "Developer", "Tester");

        Flux<String> fromFirstFluxSource = Flux.firstWithSignal(employeeFlux, profession);

        StepVerifier.create(fromFirstFluxSource)
                .expectNext("Electrical Engineer")
                .expectNext("Developer")
                .expectNext("Tester")
                .verifyComplete();
    }


    @Test
    public void zip_Fluxes_to_object() {
        Flux<String> employeeFlux = Flux.just("Amit", "Sumit", "Ajeet");

        Flux<String> profession = Flux.just("Electrical Engineer", "Developer", "Tester");

        Flux<String> mergedFlux = Flux.zip(employeeFlux, profession, (e, p) -> e + " is " + p);

        StepVerifier.create(mergedFlux)
                .expectNext("Amit is Electrical Engineer")
                .expectNext("Sumit is Developer")
                .expectNext("Ajeet is Tester")
                .verifyComplete();
    }

    @Test
    public void merge_two_flux_using_zip() {
        Flux<String> employeeFlux = Flux.just("Amit", "Sumit", "Ajeet");

        Flux<String> profession = Flux.just("Electrical Engineer", "Developer", "Tester");

        Flux<Tuple2<String, String>> mergedFlux = Flux.zip(employeeFlux, profession);

        StepVerifier.create(mergedFlux)
                .expectNextMatches(p -> p.getT1().equals("Amit") &&
                        p.getT2().equals("Electrical Engineer"))
                .expectNextMatches(p -> p.getT1().equals("Sumit") &&
                        p.getT2().equals("Developer"))
                .expectNextMatches(p -> p.getT1().equals("Ajeet") &&
                        p.getT2().equals("Tester"))
                .verifyComplete();

    }


    @Test
    public void merge_two_flux_using_merge() {
        Flux<String> employeeFlux = Flux.just("Amit", "Sumit", "Ajeet")
                .delayElements(Duration.ofMillis(500));

        Flux<String> profession = Flux.just("Electrical Engineer", "Developer", "Tester")
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));

        Flux<String> mergedFlux = employeeFlux.mergeWith(profession);

        StepVerifier.create(mergedFlux)
                .expectNext("Amit")
                .expectNext("Electrical Engineer")
                .expectNext("Sumit")
                .expectNext("Developer")
                .expectNext("Ajeet")
                .expectNext("Tester")
                .verifyComplete();

    }


}
