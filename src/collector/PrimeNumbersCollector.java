package collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static collect.Prime.isPrime;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>>{
                                                    // 스트림 요소의 형식, 누적자 형식, 수집 연산의 결과 형식
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){{//두 개의 빈 리스트를 포함하는 맵으로 수집 동작을 시작한다.
            put(true, new ArrayList<Integer>());
            put(false, new ArrayList<Integer>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
            acc.get( isPrime(acc.get(true), candidate) )//isPrime 결과에 따라 소수 리스트와 비소수 리스트를 만든다.
                    .add(candidate);// candidate를 알맞은 리스트에 추가한다.
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();//최종 수집 과정에서 데이터 변환이 필요하지 않으므로 항등 함수를 반환한다.
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) ->{//두 번째 맵을 첫 번째 맵에 병합한다.
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Set<java.util.stream.Collector.Characteristics> charactristics() {
        return Collections.unmodifiableSet(EnumSet.of(java.util.stream.Collector.Characteristics.IDENTITY_FINISH));
        //발견한 소수의 순서에 의미가 있으므로 컬렉터는 identity_finish지만 unordered, concurrent는 아니다.
    }

}

