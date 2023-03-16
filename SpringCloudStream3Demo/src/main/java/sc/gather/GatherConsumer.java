package sc.gather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author roy
 * @desc
 */
@Configuration
public class GatherConsumer {

    /**
     * 例如这样的 Function，就需要声明 gather-in-0 和 gather-in-1
     * 如果输出的是个 Tuple，那就同样需要声明 gather-out-0 和 gather-out-1
     */
    @Bean
    public Function<Tuple2<Flux<String>, Flux<String>>, Flux<String>> gather() {
        return tuple -> {
            // 123
            Flux<String> t1 = tuple.getT1();
            // gather2:abc
            Flux<String> t2 = tuple.getT2().map(str -> "gather2:" + str);
            // return Flux.merge(t1,t2);
            // gather1:123;gather2:abc
            return Flux.combineLatest(t1, t2, (str1, str2) -> "gather1:" + str1 + ";" + str2);
        };
    }

    @Bean
    public Consumer<String> gatherEcho(){
        return message -> System.out.println("received message from gatherCosuer : " + message);
    }

}
