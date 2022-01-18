package collect;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Menu {
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

        //counting() - 팩트리 메서드가 반환하는 컬렉터로 메뉴에서 요리 수를 계산한다.
        long howManyDishes = menu.stream().collect(Collectors.counting());
        long howManyDishes2 = menu.stream().count();
        System.out.println("메뉴 요리 수 = "+ howManyDishes);//메뉴 요리 수 = 9
        System.out.println("메뉴 요리 수2 = "+ howManyDishes2);//메뉴 요리 수2 = 9

        //최댓값과 최솟값
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        //Optional<Dish>의 역할 - menu가 비어있을 경우 그 어떤 요리도 반환되지 않을 것.
        System.out.println(mostCalorieDish);//Optional[Dish{name='pork}]

        //메뉴 리스트의 총 칼로리를 계산
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("총칼로리 : " + totalCalories);//총칼로리 : 4200

        //평균
        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println("칼로리 평균 : " + avgCalories);//칼로리 평균 : 466.6666666666667

        //연산 한번에 수집
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);//IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}

        //메뉴의 모든 요리명을 연결하는 코드
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);//porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon
       // String shortMenu2 = menu.stream().collect(joining());
        // -- Dish클래스가 요리명을 반환하는 toString 메서드를 포함하고 있다면 map으로 각 요리의 이름을 추출하는 과정 생략 가능
        String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println("요리명 : " + shortMenu2); // 요리명 : pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon

        // 범용 리듀싱 연산 요약
        // 3개 인수
        int totalCalories_ = menu.stream().collect(reducing(0, Dish::getCalories, (i,j)->i+j));
        System.out.println("칼로리 총합 : " + totalCalories_);//칼로리 총합 : 4200

        // 1개 인수
        Optional<Dish> mostCaloriesDish = menu.stream().collect(reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1: d2
        ));
        System.out.println(mostCaloriesDish);//Optional[Dish{name='pork}]

        //collect와 reduce의 차이점
        Stream<Integer> stream = Arrays.asList(1,2,3,4,5,6).stream();
        List<Integer> numbers = stream.reduce(
                new ArrayList<Integer>(),
                (List<Integer> l, Integer e) ->{
                    l.add(e);
                    return l;
                },
                (List <Integer> l1, List<Integer> l2) ->{
                    l1.addAll(l2);
                    return l1;
                });
        System.out.println(numbers);//[1, 2, 3, 4, 5, 6]

        int totalCaories = menu.stream().collect(reducing(0, // 초깃값
                                                Dish::getCalories,  // 합계 함수
                                                Integer::sum));//변환 함수
        System.out.println(totalCaories);//4200

        int totalCaloreis = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println(totalCaloreis);//4200

        int totalCalories2 = menu.stream().mapToInt(Dish::getCalories).sum();
    }
}
