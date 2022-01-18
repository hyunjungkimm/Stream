package collect;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Partition {
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

        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian)); // 분할 함수
        System.out.println(partitionedMenu);//{false=[Dish{name='pork}, Dish{name='beef}, Dish{name='chicken}, Dish{name='prawns}, Dish{name='salmon}], true=[Dish{name='french fries}, Dish{name='rice}, Dish{name='season fruit}, Dish{name='pizza}]}

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println(vegetarianDishes); //[Dish{name='french fries}, Dish{name='rice}, Dish{name='season fruit}, Dish{name='pizza}]

        List<Dish> vegetarianDishes2 = menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println(vegetarianDishes2);//[Dish{name='french fries}, Dish{name='rice}, Dish{name='season fruit}, Dish{name='pizza}]

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream()
                                                                    .collect(partitioningBy(Dish::isVegetarian, //분할함수
                                                                            groupingBy(Dish::getType)));//두 번째 컬렉터
        System.out.println(vegetarianDishesByType);//{false={FISH=[Dish{name='prawns}, Dish{name='salmon}], MEAT=[Dish{name='pork}, Dish{name='beef}, Dish{name='chicken}]}, true={OTHER=[Dish{name='french fries}, Dish{name='rice}, Dish{name='season fruit}, Dish{name='pizza}]}}

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
            menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

        System.out.println(mostCaloricPartitionedByVegetarian);//{false=Dish{name='pork}, true=Dish{name='pizza}}

        Map<Boolean, Map<Boolean, List<Dish>>> menut = menu.stream().collect(partitioningBy(Dish::isVegetarian,
                            partitioningBy(d->d.getCalories() > 500)));
        System.out.println(menut);
        //{false={false=[Dish{name='chicken}, Dish{name='prawns}, Dish{name='salmon}], true=[Dish{name='pork}, Dish{name='beef}]}, true={false=[Dish{name='rice}, Dish{name='season fruit}], true=[Dish{name='french fries}, Dish{name='pizza}]}}

        // x - partitioningBy는 불리언 반환하는함수, 즉 프레디케이트를 요구하므로 컴파일되지 않는다.
        // menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(Dish::getType)));

        Map<Boolean, Long> dd = menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
        System.out.println(dd);//{false=5, true=4}
    }
}
