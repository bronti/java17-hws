package ru.au.yaveyn.java.functional.test;

import org.junit.Test;
import ru.au.yaveyn.java.functional.Function1;
import ru.au.yaveyn.java.functional.Function2;

import static org.junit.Assert.assertEquals;

/**
 * Created by bronti on 06.04.17.
 */
public class Function2Test {

    @Test
    public void testSimple() {
        Function2<Integer, Integer, Integer> sum = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer run(Integer par1, Integer par2) {
                return par1 + par2;
            }
        };

        Integer num = 0;
        assertEquals(num, sum.run(17, -17));
    }

    @Test
    public void testCompose() {
        Function2<Integer, Integer, Integer> sum = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer run(Integer par1, Integer par2) {
                return par1 + par2;
            }
        };
        Function1<Integer, Integer> pow2 = new Function1<Integer, Integer>() {
            @Override
            public Integer run(Integer par) {
                return par * par;
            }
        };

        Integer num = 25;
        assertEquals(num, sum.compose(pow2).run(2, 3));
    }

    @Test
    public void testDifferentTypesCompose() {
        Function2<String, Integer, Double> f1 = new Function2<String, Integer, Double>() {
            @Override
            public Double run(String par1, Integer par2) {
                return Math.sqrt(Integer.parseInt(par1) + par2);
            }
        };
        Function1<Double, Integer> f2 = new Function1<Double, Integer>() {
            @Override
            public Integer run(Double par) {
                return (new Double(par * par)).intValue();
            }
        };

        Integer num = 239;
        assertEquals(num, f1.compose(f2).run("0", 239));
    }

    @Test
    public void testBind1() {
        Function2<Integer, Integer, Double> pow = new Function2<Integer, Integer, Double>() {
            @Override
            public Double run(Integer par1, Integer par2) {
                return Math.pow(par1, par2);
            }
        };

        Double num = 25.0;
        assertEquals(num, pow.bind1(5).run(2));
    }

    @Test
    public void testBind2() {
        Function2<Integer, Integer, Double> pow = new Function2<Integer, Integer, Double>() {
            @Override
            public Double run(Integer par1, Integer par2) {
                return Math.pow(par1, par2);
            }
        };

        Double num = 25.0;
        assertEquals(num, pow.bind2(2).run(5));
    }

    @Test
    public void testCurry() {
        Function2<Integer, Integer, Double> pow = new Function2<Integer, Integer, Double>() {
            @Override
            public Double run(Integer par1, Integer par2) {
                return Math.pow(par1, par2);
            }
        };

        Double num = 25.0;
        assertEquals(num, pow.curry().run(5).run(2));
    }

}
