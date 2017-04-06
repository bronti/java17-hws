package ru.au.yaveyn.java.functional.test;

import org.junit.Test;
import ru.au.yaveyn.java.functional.Function1;
import ru.au.yaveyn.java.functional.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by bronti on 06.04.17.
 */
public class PredicateTest {

    @Test
    public void testAlwaysTrue() {
        assertTrue(Predicate.ALWAYS_TRUE.run(178));
        assertTrue(Predicate.ALWAYS_TRUE.run("smth"));
        assertTrue(Predicate.ALWAYS_TRUE.run(2.0));
    }

    @Test
    public void testAlwaysFalse() {
        assertFalse(Predicate.ALWAYS_FALSE.run(178));
        assertFalse(Predicate.ALWAYS_FALSE.run("smth"));
        assertFalse(Predicate.ALWAYS_FALSE.run(2.0));
    }

    @Test
    public void testAnd() {
        Predicate<Integer> non_positive = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par <= 0;
            }
        };

        Predicate<Integer> non_negative = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par >= 0;
            }
        };

        assertTrue(non_negative.and(non_positive).run(0));
        assertFalse(non_negative.and(non_positive).run(9));
    }

    @Test
    public void testOr() {
        Predicate<Integer> non_positive = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par <= 0;
            }
        };

        Predicate<Integer> non_negative = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par >= 0;
            }
        };

        assertTrue(non_negative.or(non_positive).run(0));
        assertTrue(non_negative.or(non_positive).run(9));
    }

    @Test
    public void testNot() {
        Predicate<Integer> non_positive = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par <= 0;
            }
        };

        assertFalse(non_positive.not().run(0));
        assertTrue(non_positive.not().run(8));
    }


}
