package ru.au.yaveyn.java.streams;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static ru.au.yaveyn.java.streams.SecondPartTasks.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        System.out.println(System.getProperty("user.dir"));
        HashMultiset<String> result1 = HashMultiset.create(Arrays.asList(
                "Integer sapien lectus, viverra at odio eu, accumsan faucibus libero.",
                "Sed porta libero at luctus sagittis.",
                "Nunc a mollis metus, mollis euismod libero.",
                "Praesent aliquam, nunc at auctor posuere, neque quam consequat risus, at luctus erat nunc sed libero.",
                "Sed porta libero at luctus sagittis.",
                "Nunc a mollis metus, mollis euismod libero."
        ));
        HashMultiset<String> result2 = HashMultiset.create(Arrays.asList(
                "Sed porta libero at luctus sagittis.",
                "Nunc a mollis metus, mollis euismod libero."
        ));

        assertEquals(
                result1,
                HashMultiset.create(
                        findQuotes(Arrays.asList("testData/emptyFile.txt", "testData/anotherFile.txt", "testData/loremIpsum.txt"),
                                "libero")));

        assertEquals(
                result2,
                HashMultiset.create(findQuotes(Arrays.asList("testData/emptyFile.txt", "testData/anotherFile.txt"), "libero")));

        assertEquals(
                HashMultiset.create(),
                HashMultiset.create(findQuotes(Collections.singletonList("testData/emptyFile.txt"), "libero")));

        assertEquals(
                HashMultiset.create(),
                HashMultiset.create(findQuotes(Collections.emptyList(), "libero")));
    }

    @Test
    public void testPiDividedBy4() {
        Double eps = 0.001;
        Double result = Math.PI / 4.;

        // надо бы зафиксировать seed, но глобально вроде нельзя, а менять сигнатуру функции не хочется.
        assertTrue(Math.abs(piDividedBy4() - result) < eps);
        assertTrue(Math.abs(piDividedBy4() - result) < eps);
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> data = new HashMap<>();
        assertEquals(null, findPrinter(data));

        data.put("J K Rowling", Arrays.asList("HP1", "HP2", "HP3", "HP4", "HP5", "HP ... WTF is going on?", "What have you done to my Severus, beatch?!"));
        data.put("Me", Collections.emptyList());
        data.put("Umberto Eco", Arrays.asList("Something about finger which hurts.", "Well, guys, let's talk about gypsy's urbanization", "My ship is a time machine!", "Redbeard... And not a dog!"));

        assertEquals("Umberto Eco", findPrinter(data));
    }

    @Test
    public void testCalculateGlobalOrder() {
        Map<String, Integer> me = new HashMap<>();
        Map<String, Integer> slava = new HashMap<>();
        Map<String, Integer> mum = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();

        assertEquals(result, calculateGlobalOrder(Collections.emptyList()));
        assertEquals(result, calculateGlobalOrder(Arrays.asList(me, slava, mum)));

        me.put("Kinder Chocolate", 5);
        result.put("Kinder Chocolate", 5);
        assertEquals(result, calculateGlobalOrder(Arrays.asList(me, slava, mum)));

        slava.put("Expensive tea", 500);
        result.put("Expensive tea", 500);
        assertEquals(result, calculateGlobalOrder(Arrays.asList(me, slava, mum)));

        me.put("Expensive tea", 100);
        result.put("Expensive tea", result.get("Expensive tea") + 100);
        assertEquals(result, calculateGlobalOrder(Arrays.asList(me, slava, mum)));

        mum.put("Meat", 500);
        result.put("Meat", 500);
        assertEquals(result, calculateGlobalOrder(Arrays.asList(me, slava, mum)));

    }
}