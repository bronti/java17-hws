package ru.au.yaveyn.java.functional;

/**
 * Created by bronti on 06.04.17.
 */
public abstract class Function1<P, R> {

    public abstract R run(P par);

    public <C> Function1<P, C> compose(final Function1<? super R, ? extends C> outer) {
        return new Function1<P, C>() {
            @Override
            public C run(final P par) {
                return outer.run(Function1.this.run(par));
            }
        };
    }

}
