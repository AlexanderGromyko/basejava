package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsTest {
    public static void main(String[] args) {
        System.out.println(minValue(new int[] {1,2,3,3,2,3}));
        System.out.println(minValue(new int[] {9,8}));

        System.out.println(oddOrEven(List.of(new Integer[]{1, 1, 1, 1, 1}))); //сумма нечётная, надо вернуть массив четных (пустой)
        System.out.println(oddOrEven(List.of(new Integer[]{1, 3, 1, 40, 32}))); //сумма нечётная, надо вернуть массив четных
        System.out.println(oddOrEven(List.of(new Integer[]{1, 2, 3, 4, 5, 6, 7}))); //сумма чётная, надо вернуть массив нёчетных
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (acc, x) -> acc*10 + x);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
         Map <Boolean, List<Integer>> map  = integers.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        return map.get(false).size() % 2 == 0 ? map.get(false) : map.get(true);
    }
}