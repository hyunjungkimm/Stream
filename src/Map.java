import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Map {
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

        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        System.out.println(dishNames); //[pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
        System.out.println("----------------------");

        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());

        System.out.println(wordLengths);//[6, 4, 2, 6]
        System.out.println("----------------------");

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());

        System.out.println(dishNameLengths);//[4, 4, 7, 12, 4, 12, 5, 6, 6]
        System.out.println();
/*
        List<String> word = Arrays.asList("Hello", "World");
        List<String> wordOne = word.stream()
                .map(word ->word.split(""))
                .distinct()
                .collect(toList());
*/
        //문자열을 stream으로 만드는 Arrays.stream 메서드
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        List<String> word_ = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = word_.stream()
                .map(word ->word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters);//[H, e, l, o, W, r, d]

        List<Integer> number = Arrays.asList(1,2,3,4,5);

        List<Integer> doubleNumber = number.stream()
                .map(i-> i*i)
                .collect(toList());

        System.out.println(doubleNumber);


        List<Integer> numberList1 = Arrays.asList(1,2,3);
        List<Integer> numberList2 = Arrays.asList(3,4);
        List<int[]> pairs = numberList1.stream()
                .flatMap(i->numberList2.stream()
                                        .map(j->new int[]{i,j}))
                .collect(toList());

        for(int[] i : pairs){
            System.out.println(Arrays.toString(i));//[1, 3],[1, 4],[2, 3],[2, 4],[3, 3],[3, 4],
        }

        System.out.println("-------------------------------");

        List<List<Integer>> paris2 = new ArrayList<>();
        paris2.stream().forEach(
                it -> {
                    it.forEach(
                            System.out::println
                    );
                }
        );

        List<int[]> dividePairs = numberList1.stream()
                .flatMap(i->numberList2.stream()
                        .filter(j -> (i+j)%3 == 0)
                        .map(j->new int[]{i,j}))
                .collect(toList());

        System.out.println(dividePairs);
    }

}
