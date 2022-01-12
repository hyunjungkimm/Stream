import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Operation {
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
        List<String> names = menu.stream()
                .filter(dish ->{System.out.println("filtering:" + dish.getName());
                                return dish.getCalories() > 300;
                })//필터링한 요리명을 출력한다.
                .map(dish ->{System.out.println("mapping:" + dish.getName());
                    return dish.getName();
                })//추출한 요리명을 출력한다.
                .limit(3)
                .collect(toList());
        System.out.println(names);
        //filtering:pork
        //mapping:pork
        //filtering:beef
        //mapping:beef
        //filtering:chicken
        //mapping:chicken
        //[pork, beef, chicken
        System.out.println();

        menu.stream().forEach(System.out::println);//forEach 최종연산
        System.out.println();


        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();
        System.out.println(count);

    }
}
