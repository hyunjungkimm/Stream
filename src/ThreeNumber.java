import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ThreeNumber {
    public static void main(String[] args) {

        /*
        if(Math.sqrt(3*3 + 4*4) %1 == 0){
            System.out.println("피타고라스 수");
        };

        System.out.println(Math.sqrt(3*3 + 4*4)%1);

        //filter(b->Math.sqrt(a*a + b*b) %1 ==0); //a라는 값이 주어지고 b는 스트림으로 제공된다고 가정할떄 filter로 a와 함께 피타고라스 수를 구성하는 모든 b를 필터링할 수 있다.

        Arrays.stream(arr).filter(b->Math.sqrt(a*a + b*b)%1 ==0)
                .map(b -> new int[]{a,b,(int)Math.sqrt(a * a + b * b)});//마지막 세 번쨰 수를 찾기
        */

       /* int a = 0;
        IntStream.rangeClosed(1,100)
                .filter(b->Math.sqrt(a*a + b*b) % 1 ==0)
                .boxed()
                .map(b-> new int[]{a,b, (int)Math.sqrt(a*a + b*b)});

        IntStream.rangeClosed(1,100)
                .filter(b->Math.sqrt(a*a + b*b) %1 == 0)
                .mapToObj(b->new int[]{a,b, (int)Math.sqrt(a*a + b*b)});
*/
        Stream<int[]> pythaoreanTriples = IntStream.rangeClosed(1,100).boxed()
                                                    .flatMap(a ->
                                                            IntStream.rangeClosed(a,100)
                                                                    .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                                                    .mapToObj(b -> new int[]{a,b, (int)Math.sqrt(a*a + b*b)}));

        pythaoreanTriples.limit(5)
                .forEach(t-> System.out.println(t[0] +", "+ t[1] + ", " + t[2]));

        // 개선점 - 제곱근으 두 번 계산한다. 따라서 (a*a , b*b , a*a+b*b) 형식을 만족하는 세 수를 만든 다음에 우리가 원하는 조건에 맞는 결과만 필터링하는 것이 더 최적화
        Stream<double[]> pythaoreanTriples2 =
                IntStream.rangeClosed(1,100).boxed()
                         .flatMap(a->
                                    IntStream.rangeClosed(a,100)
                                             .mapToObj(b->new double[]{a,b,Math.sqrt(a*a + b*b)})
                                            .filter(t->t[2] % 1 ==0));//세 수의 세번째 요소는 반드시 정수여야 한다.

        pythaoreanTriples2.limit(5)
                .forEach(t-> System.out.println(t[0] +", " + t[1] +", " + t[2]));
    }
}
