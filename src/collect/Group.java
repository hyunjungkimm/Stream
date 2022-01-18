package collect;


import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Group {
    public static void main(String[] args) {
        List<Dish> menu = asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));
        //{OTHER=[Dish{name='french fries}, Dish{name='rice}, Dish{name='season fruit}, Dish{name='pizza}], MEAT=[Dish{name='pork}, Dish{name='beef}, Dish{name='chicken}], FISH=[Dish{name='prawns}, Dish{name='salmon}]}

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 400) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println(dishesByCaloricLevel);
        //{FAT=[Dish{name='pork}, Dish{name='beef}, Dish{name='french fries}, Dish{name='pizza}, Dish{name='salmon}], DIET=[Dish{name='chicken}, Dish{name='rice}, Dish{name='season fruit}, Dish{name='prawns}]}

    //그룹화된 요소 조작
        //500칼로리가 넘는 요리만 필터
        Map<Dish.Type, List<Dish>> caloricDishesByType =
                menu.stream().filter(dish -> dish.getCalories() > 500)
                        .collect(groupingBy(Dish::getType));

        Map<Dish.Type, List<Dish>> caloricDishesByType2 =
                menu.stream()
                        .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println(caloricDishesByType2);

        //그룹의 각 요리를 관련 이름 목록으로 변환
        Map<Dish.Type, List<String>> dishNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

        System.out.println(dishesByType);

        //문자열 리스트
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        Map<Dish.Type, Set<String>> dishNamesByType2 =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                flatMapping(dish -> dishTags.get( dish.getName()  ).stream(), toSet())));

        System.out.println(dishNamesByType2);
        //{MEAT=[salty, greasy, roasted], FISH=[roasted, tasty, fresh, delicious], OTHER=[salty, greasy, natural, light, tasty, fresh, fried]}

        //다수준 그룹화
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        groupingBy(Dish::getType, //첫 번째 수준의 분류 함수
                                groupingBy(dish ->{ //두 번째 수준의 분류 함수
                                    if(dish.getCalories() <= 400)
                                        return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700)
                                        return CaloricLevel.NORMAL; else return CaloricLevel.FAT;
                                })
                        )
                );
        System.out.println(dishesByTypeCaloricLevel);
        //{OTHER={NORMAL=[Dish{name='french fries}, Dish{name='pizza}], DIET=[Dish{name='rice}, Dish{name='season fruit}]}, MEAT={NORMAL=[Dish{name='beef}], FAT=[Dish{name='pork}]}, FISH={NORMAL=[Dish{name='salmon}], DIET=[Dish{name='prawns}]}}

        //서브그룹으로 데이터 수집
        //메뉴에서 요리의 수를 종류별로 계산
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);//{MEAT=2, FISH=2, OTHER=4}

        //요리의 종류를 분류하는 컬렉터로 메뉴에서 가장 높은 칼로리를 가진 요리를 찾는 프로그램 구현
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                        maxBy(comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);//{OTHER=Optional[Dish{name='pizza}], MEAT=Optional[Dish{name='pork}], FISH=Optional[Dish{name='salmon}]}

        //서브그룹에서 가장 칼로리가 높은 요리 찾기
        Map<Dish.Type, Dish> mostCalroicByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get
                                )));
        System.out.println(mostCalroicByType);//{OTHER=Dish{name='pizza}, MEAT=Dish{name='pork}, FISH=Dish{name='salmon}}

        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);

        //mapping 메서드
        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(dish -> {
                            if(dish.getCalories() <=400 ) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }, toSet())));

        System.out.println(caloricLevelsByType);//{OTHER=[NORMAL, DIET], MEAT=[NORMAL, FAT], FISH=[NORMAL, DIET]}

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(dish -> {
                            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }, toCollection(HashSet::new) )));//{OTHER=[NORMAL, DIET], MEAT=[NORMAL, FAT], FISH=[NORMAL, DIET]}

        System.out.println(caloricLevelsByType2);

    }
}
