import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Match {
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

        //menu에 채식 요리가 있는지 확인
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        //메뉴가 건강식(모든 메뉴가 1000칼로리 이하면 건강식으로 간주)
        if(menu.stream().allMatch(dish -> dish.getCalories() <1000)){
            System.out.println("모든 메뉴가 건강식이다.");
        }

        boolean isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
        if(isHealthy){
            System.out.println("모든 메뉴가 건강식이다!!");
        }

        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        System.out.println(dish);//Optional[Dish{name='french fries}]

        menu.stream()
        .filter(Dish::isVegetarian)
        .findAny()
        .ifPresent(dishes -> System.out.println(dishes.getName()));//french fries

        //숫자리스트에서 3으로 나누어 떨어지는 첫 번째 제곱값을 반환한다
        List<Integer> number = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree = number.stream()
                .map(i->i*i)
                .filter(i -> i%3 ==0)
                .findFirst();

        System.out.println(firstSquareDivisibleByThree);//Optional[9]
    }
}
