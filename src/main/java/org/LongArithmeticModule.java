package org;

import com.google.inject.AbstractModule;

public class LongArithmeticModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LongArithmethic.class).to(LongArithmeticImplList.class);
    }
}
