import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class slicing {
    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER)
                );

        //320칼로리 이하
        List<Dish> sliceMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        System.out.println(sliceMenu1);//[Dish{name='seasonal fruit}, Dish{name='prawns}]

        System.out.println();

        //320칼로리 초과
        List<Dish> sliceMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toList());
        System.out.println(sliceMenu2);//[Dish{name='rice}, Dish{name='chicken}, Dish{name='french fries}]
    }
}
