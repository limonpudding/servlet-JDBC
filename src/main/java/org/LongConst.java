package org;

enum LongConst {
    ZERO(new LongArithmeticImplList("0")),
    ONE(new LongArithmeticImpl("1")),
    TWO(new LongArithmeticImplList("2"));

    private LongArithmethic value;

    public LongArithmethic getValue() {
        return value;
    }

    LongConst(LongArithmethic value) {
        this.value = value;
    }

}
