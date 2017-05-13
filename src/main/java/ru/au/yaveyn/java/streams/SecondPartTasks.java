package ru.au.yaveyn.java.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {
    }

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths
                .stream()
                .map(Paths::get)
                .flatMap(it -> {
                    try {
                        return Files.lines(it);
                    } catch (IOException ex) {
                        // не было сказано как обрабатывать IOException, так что я его просто игнорирую...
                        return Stream.empty();
                    }
                })
                .filter(it -> it.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random r = new Random();
        int n_iter = 1000000;
        return Stream.generate(() -> {
            double x = 2 * r.nextDouble() - 1.;
            double y = 2 * r.nextDouble() - 1.;
            return (x * x + y * y) < 1 ? 1 : 0;
        }).limit(n_iter).collect(Collectors.averagingDouble(Integer::doubleValue));
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions
                .keySet()
                .stream()
                .max(Comparator.comparing(it -> compositions.get(it).stream().mapToInt(String::length).sum()))
                .orElse(null);
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders
                .stream()
                .flatMap(it -> it.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.reducing(0, Map.Entry::getValue, (i, j) -> i + j)));
    }
}
