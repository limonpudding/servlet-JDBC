package org;


public interface LongArithmethic extends Comparable<LongArithmethic> {

    byte[] getDigits();

    void setLength(int length);

    byte getDigit(int index);

    void setDigit(byte digit, int index);

    int getLength();

    int getLengthMul();

    Sign getSign();

    void setSign(Sign sign);

    String toString();


}
