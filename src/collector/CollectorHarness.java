package collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static collect.Prime.isPrime;

public class CollectorHarness {
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for(int i = 0; i < 10; i++){//테스트를 10번 반복한다.
            long start = System.nanoTime();
            partitionPrimes(1_000_0000);//백만개의 숫ㅈ를 소수와 비소수로 분할한다
            long duration = (System.nanoTime() - start) /1_000_000;//duration을 밀리초 단위로 측정한다.
            if(duration < fastest) fastest = duration; //가장 빨리 실행되었는지 확인한다.

        }
        System.out.println("Fastest execution done in "+ fastest + " msecs");
    }



    public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n){
        return (Map<Boolean, List<Integer>>) IntStream.rangeClosed(2,n).boxed()
                .collect(
                        () -> new HashMap<Boolean,List<Integer>>(){{
                    put(true, new ArrayList<Integer>());
                    put(false, new ArrayList<Integer>());
        }},
                        (acc, candidate) ->{
                            acc.get(isPrime(acc.get(true), candidate) )
                                    .add(candidate);
                        },
                        (map1 , map2 )-> {
                            map1.get(true).addAll(map2.get(true));
                            map1.get(false).addAll(map2.get(false));
                        });
    }

    private static void partitionPrimes(int i) {
    }
}
