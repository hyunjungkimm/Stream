package collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;//수집 연산의 시발점
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add; // 탐색한 항목을 누적하고 바로 누적자를 고친다.
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();//항등함수
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) ->{
            list1.addAll(list2);//두 번째 컨텐츠와 합쳐서 첫 번째 누적자를 고친다.
            return list1;//변경된 첫 번째 누적자를 반환한다.
        };
    }

    @Override
    public Set<java.util.stream.Collector.Characteristics> charactristics() {
        return Collections.unmodifiableSet(EnumSet.of(java.util.stream.Collector.Characteristics.IDENTITY_FINISH, java.util.stream.Collector.Characteristics.CONCURRENT));
        //컬렉터의 IDENTITY_FINISH, CONCURRENT로 설정한다.
    }
}


