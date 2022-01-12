import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class iteration {
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
        //for each문
        List<String> name = new ArrayList<>();
        for(Dish dish : menu){//메뉴 리스트를 명시적으로 순차반복한다.
            name.add(dish.getName());//이름을 추출해서 리스트에 추가한다.
        }
        System.out.println(name);//[pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
        System.out.println();
        //컬렉션: 내부적으로 숨겨졌던 박복자를 사용한 외부 반복
        List<String> name2 = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();

        while (iterator.hasNext()){//명시적 반복
            Dish dish = iterator.next();
            name2.add(dish.getName());
        }
        System.out.println(name2);//[pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
        System.out.println();
        //스트림 : 내부반복
        List<String> name3 = menu.stream()
                                 .map(Dish::getName)//map 메서드를 getName메서드로 파라미터화해서 요리명을 추출한다.
                                 .collect(toList());//파이프라인을 실행한다. 반복자는 필요 없다.
        System.out.println(name3);//[pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
        System.out.println();
        //외부반복
        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator1 = menu.iterator();
        while(iterator1.hasNext()){
            Dish dish = iterator1.next();
            if(dish.getCalories() > 300){
                highCaloricDishes.add(dish.getName());
            }
        }
        System.out.println(highCaloricDishes);//[pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
        System.out.println();
        //내부반복
        List<String> highCaloricDishes2 = menu.stream()
                .filter((dish) -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());
        System.out.println(highCaloricDishes2);//[pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
    }
}
