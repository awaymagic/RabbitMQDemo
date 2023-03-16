package com.away;

import reactor.core.publisher.Flux;

/**
 * @author roy
 * @desc
 */
public class FluxDemo {

    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("a", "b", "c");
        Flux<String> flux2 = Flux.just("1", "2", "3");
        // flux1.subscribe(System.out::println);
        // 合并
        // Flux res = Flux.merge(flux1, flux2);
        // flux1最后一个元素 flux2任何一个元素
        Flux<String> res = Flux.combineLatest(flux1, flux2, (v1, v2) -> v1 + ":" + v2);
        //
        res.subscribe(System.out::println);
    }

}
