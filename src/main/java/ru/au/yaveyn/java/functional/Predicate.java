package ru.au.yaveyn.java.functional;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by bronti on 06.04.17.
 */
public abstract class Predicate<P> extends Function1<P, Boolean> {

    public static final Predicate<Object> ALWAYS_TRUE = new Predicate<Object>() {
        @Override
        public Boolean run(final Object par) {
            return true;
        }
    };

    public static final Predicate<Object> ALWAYS_FALSE = new Predicate<Object>() {
        @Override
        public Boolean run(final Object par) {
            return false;
        }
    };

    public Predicate<P> or(final Predicate<? super P> other) {
        return new Predicate<P>() {
            @Override
            public Boolean run(final P par) {
                return Predicate.this.run(par) || other.run(par);
            }
        };
    }

    public Predicate<P> and(final Predicate<? super P> other) {
        return new Predicate<P>() {
            @Override
            public Boolean run(final P par) {
                return Predicate.this.run(par) && other.run(par);
            }
        };
    }

    public Predicate<P> not() {
        return new Predicate<P>() {
            @Override
            public Boolean run(final P par) {
                return ! Predicate.this.run(par);
            }
        };
    }

}
