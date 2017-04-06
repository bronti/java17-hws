package ru.au.yaveyn.java.functional;

/**
 * Created by bronti on 06.04.17.
 */
public abstract class Function2<P1, P2, R> {

    public abstract R run(P1 par1, P2 par2);

    public <C> Function2<P1, P2, C> compose(final Function1<? super R, ? extends C> outer) {
        return new Function2<P1, P2, C>() {
            @Override
            public C run(final P1 par1, final P2 par2) {
                return outer.run(Function2.this.run(par1, par2));
            }
        };
    }

    public Function1<P2, R> bind1(final P1 par1) {
        return new Function1<P2, R>() {
            @Override
            public R run(final P2 par2) {
                return Function2.this.run(par1, par2);
            }
        };
    }

    public Function1<P1, R> bind2(final P2 par2) {
        return new Function1<P1, R>() {
            @Override
            public R run(final P1 par1) {
                return Function2.this.run(par1, par2);
            }
        };
    }

    public Function1<P1, Function1<P2, R>> curry() {
        return new Function1<P1, Function1<P2, R>>() {
            @Override
            public Function1<P2, R> run(final P1 par1) {
                return new Function1<P2, R>() {
                    @Override
                    public R run(final P2 par2) {
                        return Function2.this.run(par1, par2);
                    }
                };
            }
        };
    }

}
