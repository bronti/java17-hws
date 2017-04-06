package ru.au.yaveyn.java.functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by bronti on 06.04.17.
 */
public class Collections {

    public static <P, R> Iterable<R> map(Function1<? super P, ? extends R> function, Iterable<? extends P> collection) {
        final List<R> result = new ArrayList<>();
        for (P element : collection) {
            result.add(function.run(element));
        }
        return result;
    }

    public static <P> Iterable<P> filter(Predicate<? super P> condition, Iterable<? extends P> collection) {
        final List<P> result = new ArrayList<>();
        for (P element : collection) {
            if (condition.run(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public static <P> Iterable<P> takeWhile(Predicate<? super P> condition, Iterable<? extends P> collection) {
        final List<P> result = new ArrayList<>();
        for (P element : collection) {
            if (!condition.run(element)) {
                break;
            }
            result.add(element);
        }
        return result;
    }

    // (a -> b -> a) -> a -> [b] -> a
    public static <P> Iterable<P> takeUnless(Predicate<? super P> condition, Iterable<? extends P> collection) {
        return takeWhile(condition.not(), collection);
    }

    public static <C, A> A foldl(Function2<? super A, ? super C, ? extends A> combiner,
                                 A acc,
                                 Iterable<? extends C> collection) {
        for (C element : collection) {
            acc = combiner.run(acc, element);
        }
        return acc;
    }

    // (a -> b -> b) -> b -> [a] -> b
    public static <C, A> A foldr(Function2<? super C, ? super A, ? extends A> combiner,
                                 A acc,
                                 Iterable<? extends C> collection) {
        return foldr_(combiner, acc, collection.iterator());
    }

    private static <C, A> A foldr_(Function2<? super C, ? super A, ? extends A> combiner,
                                 A acc,
                                 Iterator<? extends C> iterator) {
        if (!iterator.hasNext()) {
            return acc;
        }
        return combiner.run(iterator.next(), foldr_(combiner, acc, iterator));
    }
}
