import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reduce {
    public static void main(String[] args) {

        List<Integer> number = Arrays.asList(1,2,3,4,5);
        //for-each
        int sum = 0;
        for(int x : number){
            sum += x;
        }
        System.out.println(sum);//15

        //reduce
        int sum2 = number.stream().reduce(0, (a,b) -> a+b);
        System.out.println("합계 = " + sum2);//합계 = 15

        int product = number.stream().reduce(1, (a,b)->a*b);
        System.out.println("곱셈 = " + product);//곱셈 = 120

        int sum3 = number.stream().reduce(0, Integer::sum);
        System.out.println("합계 = "+ sum3); //합계 = 15

        //초기값 없는 reduce
        Optional<Integer> sum4 = number.stream().reduce(Integer::sum);
        System.out.println("합계 = " + sum4);//합계 = Optional[15]

        //최댓값
        Optional<Integer> max = number.stream().reduce(Integer::max);
        System.out.println("최댓값 = "+ max);//최댓값 = Optional[5]
        int max2 = number.stream().reduce(number.get(0), Integer::max);
        System.out.println("최댓값 = "+ max2);//최댓값 = 5

        //최솟값
        Optional<Integer> min = number.stream().reduce(Integer::min);
        System.out.println("최솟값 = " + min);//최솟값 = Optional[1]
        int min2 = number.stream().reduce(number.get(0), Integer::min);
        System.out.println("최솟값 = "+min2);//최솟값 = 1

        //스트림의 요리개수
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        int count =
                menu.stream()
                    .map(d->1)
                    .reduce(0, (a,b) -> a+b);
        System.out.println("요리개수 = " + count);

        long count2 = menu.stream().count();
        System.out.println("요리개수 = "+ count2);

        //스트림의 모든 요소를 더하는 코드롤 병렬로 만들기
        int sum5 = number.parallelStream().reduce(0, Integer::sum);
        System.out.println("병렬 합계 = "+ sum5);//병렬 합계 = 15
    }
}
