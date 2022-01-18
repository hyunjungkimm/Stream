package collect;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class Prime {
    public boolean isPrime(int candidate){//2부터 candidate 미만 사이의 자연수를 생성한다.
        return IntStream.range(2, candidate).noneMatch(i->candidate % i ==0);// 스트림의 모든 정수로 candidate를 나눌 수 없으면 참을 반환한다.
    }
    //제곱근
    public boolean isPrime2(int candidate){//2부터 candidate 미만 사이의 자연수를 생성한다.
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i->candidate % i ==0);// 스트림의 모든 정수로 candidate를 나눌 수 없으면 참을 반환한다.
    }

    public Map<Boolean, List<Integer>> partitionPrimees(int n){
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public static boolean isPrime(List<Integer> primes, int candidate){
        return primes.stream().noneMatch(i -> candidate % i ==0);
    }

    public static boolean isPrime2(List<Integer> primes, int candidate){
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return primes.stream()
                            .takeWhile(i -> i<=candidate)
                            .noneMatch(i -> candidate % i ==0);
    }

    public static void main(String[] args) {

    }
}
