package ru.au.yaveyn.java.functional.test;

import org.junit.Test;
import ru.au.yaveyn.java.functional.Function1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by bronti on 06.04.17.
 */

public class Function1Test {

    @Test
    public void testSimple() {
        Function1<Integer, Integer> id = new Function1<Integer, Integer>() {
            @Override
            public Integer run(Integer par) {
                return par;
            }
        };

        Integer num = 239;
        assertEquals(num, id.run(num));
    }

    @Test
    public void testCompose() {
        Function1<Integer, Integer> succ = new Function1<Integer, Integer>() {
            @Override
            public Integer run(Integer par) {
                return par + 1;
            }
        };

        Integer num = 239;
        assertEquals(num, succ.compose(succ).run(237));
    }

    @Test
    public void testDifferentTypesCompose() {
        Function1<Integer, String> toStr = new Function1<Integer, String>() {
            @Override
            public String run(Integer par) {
                return par.toString();
            }
        };
        Function1<String, Integer> toInt = new Function1<String, Integer>() {
            @Override
            public Integer run(String par) {
                return Integer.parseInt(par);
            }
        };

        assertEquals("239", toInt.compose(toStr).run("239"));
    }

}
