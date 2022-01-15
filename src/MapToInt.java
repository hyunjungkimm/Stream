import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MapToInt {
    public static void main(String[] args) {
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
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(calories);
        /*
        int sum = menu.stream()
                .map(Dish::getCalories)
                .sum(); -- 안되서 기본형 특화스트림 제공
         */
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);//스트림을 숫자 스트림으로 변환
        Stream<Integer> stream = intStream.boxed();//숫자 스트림을 스트림으로 변환

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println(maxCalories);

        //값이 없을때 기본 최댓값을 명시적으로 설정
        int max = maxCalories.orElse(1);

        System.out.println("------------------------------");
        IntStream range = IntStream.rangeClosed(1,100)//1과 100 포함o
                .filter(n -> n%2 ==0);//1부터 100까지의 짝수 스트림
        System.out.println("짝수 = " + range.count());//1부터 100까지 50개의 짝수가 있다.

        IntStream range2 = IntStream.range(1,100)//1과 100 포함x
                .filter(n -> n%2 ==0);
        System.out.println("짝수 = "+ range2.count());//2부터 99까지 49개의 짝수가 있다.
        System.out.println();

        //Stream.of - 스트림 생성
        Stream<String> streamOf = Stream.of("Modern", "Java", "In", "Action");
        streamOf.map(String::toUpperCase)
                .forEach(System.out::println);

        //Stream.empty() - 스트림 없애기
        Stream<String> emptyStream = Stream.empty();
        emptyStream.forEach(System.out::println);

        //null이 될 수 있는 객체로 스트림 만들기
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of("home");

        Stream<String> homeValueStream1 = Stream.ofNullable(System.getProperty("home"));

        Stream<String> values = Stream.of("config", "home", "user")
                                      .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        values.forEach(System.out::println);
        System.out.println();
        //배열로 스트림 만들기
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum2 = Arrays.stream(numbers).sum();
        System.out.println(sum2);//41
        }
    }


