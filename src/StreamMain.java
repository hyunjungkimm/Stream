import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamMain {
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

        List<String> lowCaloricDishesName =
                                        menu.stream()//stream()을 parallelStream()으로 바꾸면 이 코드를 멀티코어 아키텍처에서 병렬로 실행 가능능
                                       .filter(d -> d.getCalories() < 400) // 400칼로리 이하의 요리 선택
                                        .sorted(comparing(Dish::getCalories)) //칼로리로 요리 정렬
                                        .map(Dish::getName)//요리명 추출
                                        .collect(toList()); //모든 요리명을 리스트에 저장
    }
}
