package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
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
                .reduce((acc, x) -> acc*10 + x)
                .getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0))
                .entrySet().stream()
                .filter(x -> {
                    if (x.getKey()) {
                        return (integers.size() - x.getValue().size()) % 2 != 0;
                    } else return (x.getValue().size()) % 2 == 0;
                }).findFirst().get()
                .getValue();
    }
}
