package collect;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;

public class ShortMenu {
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
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu);

        String shortMenu2 = menu.stream().map(Dish::getName).collect(reducing((s1,s2) -> s1+","+s2 )).get();
        System.out.println(shortMenu2);

         /*
        String shortMenu3 = menu.stream().map(Dish::getName).collect(reducing((d1,d2) -> d1.getName()+d2.getName())).get();

        reducing은 BinaryOperator<T> , 즉 BiFunction<T,T,T> 를 인수로 받는다. 즉, reducing은 두 인수를 받아 같은 형식을 반환하는 함수를 인수로 받는다.
        두 개의 요리를 인수로 받아 문자열 반환
        */

        String shortMenu3 = menu.stream().collect(reducing("", Dish::getName, (s1,s2)->s1+s2));
        System.out.println(shortMenu3);
    }
}
