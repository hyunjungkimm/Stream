import java.util.*;
import java.util.stream.Stream;

public class Main {
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

        List<Dish> lowCaloricDishes = new ArrayList<>();
        //누적자로 요소 필터링
        for(Dish dish : menu){
            if(dish.getCalories() < 400){
                lowCaloricDishes.add(dish);
            }
        }
        //익명 클래스로 요리 정렬
        Collections.sort(lowCaloricDishes, new Comparator<Dish>(){
            public int compare(Dish dish1, Dish dish2){
                return Integer.compare(dish1.getCalories(),dish2.getCalories());
            }
        });

        //정렬된 리스트를 처리하면서 요리 이름 선택
        List<String> lowCaloricDishName = new ArrayList<>();
        for(Dish dish: lowCaloricDishes){
            lowCaloricDishName.add(dish.getName());
        }


        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);//java.lang.IllegalStateException : 스트림이 이미 소비되었거나 닫힘
    }
}
