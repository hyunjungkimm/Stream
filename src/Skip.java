import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Skip {
    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER)
        );
        //300칼로리 이상의 처음 두 요리를 건너뛴 다음에 300칼로리가 넘는 나머지 요리를 반환한다.
        List<Dish>dishes = specialMenu.stream()
                .filter(dish -> dish.getCalories() >= 300)
                .skip(2)
                .collect(toList());
        System.out.println(dishes);//[Dish{name='chicken}, Dish{name='french fries}]

        //처음 등장하는 두 고기 요리를 필터링하시오
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

        List<Dish> meatDishes = menu.stream()
                .filter(dish ->dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());

        System.out.println(meatDishes);//[Dish{name='pork}, Dish{name='beef}]
    }
}
