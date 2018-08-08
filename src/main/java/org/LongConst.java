package org;

import java.io.IOException;

enum LongConst {
    ZERO(new LongArithmeticImpl("0")),
    ONE(new LongArithmeticImpl("1")),
    TWO(new LongArithmeticImpl("2"));

    private LongArithmethic value;

    public LongArithmethic getValue() {
        return value;
    }

    LongConst(LongArithmethic value) {
        this.value = value;
    }

}
