package ru.au.yaveyn.java.functional.test;

import com.sun.jdi.DoubleValue;
import com.sun.jdi.IntegerType;
import org.junit.Test;
import ru.au.yaveyn.java.functional.Collections;
import ru.au.yaveyn.java.functional.Function1;
import ru.au.yaveyn.java.functional.Function2;
import ru.au.yaveyn.java.functional.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by bronti on 06.04.17.
 */
public class CollectionsTest {

    @Test
    public void testMap() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);

        Function1<Integer, Integer> succ = new Function1<Integer, Integer>() {
            @Override
            public Integer run(Integer par) {
                return par + 1;
            }
        };

        assertEquals(Arrays.asList(2, 3, 4), Collections.map(succ, arr));
    }

    @Test
    public void testMapOnEmpty() {
        final List<Integer> arr = java.util.Collections.emptyList();

        Function1<Integer, Integer> succ = new Function1<Integer, Integer>() {
            @Override
            public Integer run(Integer par) {
                return par + 1;
            }
        };

        assertEquals(java.util.Collections.emptyList(), Collections.map(succ, arr));
    }

    @Test
    public void testFilter() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);

        Predicate<Integer> bigger1 = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par > 1;
            }
        };

        assertEquals(Arrays.asList(2, 3), Collections.filter(bigger1, arr));
    }

    @Test
    public void testFilterOnEmpty() {
        final List<Integer> arr = java.util.Collections.emptyList();

        Predicate<Integer> bigger1 = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par > 1;
            }
        };

        assertEquals(java.util.Collections.emptyList(), Collections.filter(bigger1, arr));
    }

    @Test
    public void testFilterAllTrue() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);
        assertEquals(Arrays.asList(1, 2, 3), Collections.filter(Predicate.ALWAYS_TRUE, arr));
    }

    @Test
    public void testFilterAllFalse() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);
        assertEquals(java.util.Collections.emptyList(), Collections.filter(Predicate.ALWAYS_FALSE, arr));
    }

    @Test
    public void testTakeWhile() {
        final List<Integer> arr = Arrays.asList(3, 2, 1);

        Predicate<Integer> bigger1 = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par > 1;
            }
        };

        assertEquals(Arrays.asList(3, 2), Collections.takeWhile(bigger1, arr));
    }

    @Test
    public void testTakeWhileOnEmpty() {
        final List<Integer> arr = java.util.Collections.emptyList();

        Predicate<Integer> bigger1 = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par > 1;
            }
        };

        assertEquals(java.util.Collections.emptyList(), Collections.takeWhile(bigger1, arr));
    }

    @Test
    public void testTakeWhileAllTrue() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);
        assertEquals(Arrays.asList(1, 2, 3), Collections.takeWhile(Predicate.ALWAYS_TRUE, arr));
    }

    @Test
    public void testTakeWhileFalse() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);
        assertEquals(java.util.Collections.emptyList(), Collections.takeWhile(Predicate.ALWAYS_FALSE, arr));
    }

    @Test
    public void testTakeUnless() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);

        Predicate<Integer> bigger1 = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par > 1;
            }
        };

        assertEquals(java.util.Collections.singletonList(1), Collections.takeUnless(bigger1, arr));
    }

    @Test
    public void testTakeUnlessOnEmpty() {
        final List<Integer> arr = java.util.Collections.emptyList();

        Predicate<Integer> bigger1 = new Predicate<Integer>() {
            @Override
            public Boolean run(Integer par) {
                return par > 1;
            }
        };

        assertEquals(java.util.Collections.emptyList(), Collections.takeUnless(bigger1, arr));
    }

    @Test
    public void testTakeUnlessAllTrue() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);
        assertEquals(java.util.Collections.emptyList(), Collections.takeUnless(Predicate.ALWAYS_TRUE, arr));
    }

    @Test
    public void testTakeUnlessFalse() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);
        assertEquals(Arrays.asList(1, 2, 3), Collections.takeUnless(Predicate.ALWAYS_FALSE, arr));
    }

    @Test
    public void testFoldl() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);

        Function2<Integer, Integer, Integer> sum = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer run(Integer par1, Integer par2) {
                return par1 + par2;
            }
        };

        assertEquals(new Integer(6), Collections.foldl(sum, 0, arr));
    }

    @Test
    public void testFoldlOnEmpty() {
        final List<Integer> arr = java.util.Collections.emptyList();

        Function2<Integer, Integer, Integer> sum = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer run(Integer par1, Integer par2) {
                return par1 + par2;
            }
        };

        assertEquals(new Integer(0), Collections.foldl(sum, 0, arr));
    }

    @Test
    public void testFoldlDifferentTypes() {
        final List<Integer> arr = Arrays.asList(1, 4, 9);

        Function2<Double, Integer, Double> sqrt_sum = new Function2<Double, Integer, Double>() {
            @Override
            public Double run(Double par1, Integer par2) {
                return Math.sqrt(par2) + par1;
            }
        };

        assertEquals(new Double(6.0), Collections.foldl(sqrt_sum, 0.0, arr));
    }

    @Test
    public void testFoldr() {
        final List<Integer> arr = Arrays.asList(1, 2, 3);

        Function2<Integer, Integer, Integer> sum = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer run(Integer par1, Integer par2) {
                return par1 + par2;
            }
        };

        assertEquals(new Integer(6), Collections.foldr(sum, 0, arr));
    }

    @Test
    public void testFoldrOnEmpty() {
        final List<Integer> arr = java.util.Collections.emptyList();

        Function2<Integer, Integer, Integer> sum = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer run(Integer par1, Integer par2) {
                return par1 + par2;
            }
        };

        assertEquals(new Integer(0), Collections.foldr(sum, 0, arr));
    }

    @Test
    public void testFoldrDifferentTypes() {
        final List<Integer> arr = Arrays.asList(1, 4, 9);

        Function2<Integer, Double, Double> sqrt_sum = new Function2<Integer, Double, Double>() {
            @Override
            public Double run(Integer par1, Double par2) {
                return Math.sqrt(par1) + par2;
            }
        };

        assertEquals(new Double(6.0), Collections.foldr(sqrt_sum, 0.0, arr));
    }
}
