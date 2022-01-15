import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Iterate {
    public static void main(String[] args) {
        Stream.iterate(0, n->n+2)
                .limit(10)
                .forEach(System.out::println);// 0 - 18까지 출력

        //피보나치 수열 집합 - iterate 메서드 이용 집합 20개
        Stream.iterate(new int[]{0,1}, t-> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("("+t[0] + ","+ t[1] + ")"));
        System.out.println();

        Stream.iterate(new int[]{0,1}, t-> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
        System.out.println();

        IntStream.iterate(0, n-> n<100, n->n+4)
                .forEach(System.out::println);
        System.out.println();

        //무한생성 안되려면 takeWhile/ filter쓰면 무한생성.
        IntStream.iterate(0, n->n+4)
                .takeWhile(n->n<100)
                .forEach(System.out::println);

    }
}
