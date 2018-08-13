package org;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LongArithmeticImplList implements LongArithmethic {

    private List<Byte> digits = new ArrayList<Byte>();
    private int length = 1;

    Sign sign = Sign.PLUS;

    private boolean isDigit(char x) {
        if (x >= '0' && x <= '9') {
            return true;
        } else {
            return false;
        }
    }

    public LongArithmeticImplList(String number) throws IOException {
        length = number.length();
        if (number.charAt(0) == '-') {
            sign = Sign.MINUS;
            for (int i = length - 1; i >= 1; --i) {
                if (isDigit(number.charAt(i)))
                    digits.add((byte) (number.charAt(i) - '0'));
                else
                    throw new IOException("Incorrect long number");
            }
            length--;
        } else {
            if (number.charAt(0) == '+') {
                for (int i = length - 1; i >= 1; --i) {
                    if (isDigit(number.charAt(i)))
                        digits.add((byte) (number.charAt(i) - '0'));
                    else
                        throw new IOException("Incorrect long number");
                }
                length--;
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    if (isDigit(number.charAt(i)))
                        digits.add((byte) (number.charAt(i) - '0'));
                    else
                        throw new IOException("Incorrect long number");
                }
            }
        }
    }

    public LongArithmeticImplList() {

    }

    @Override
    public void setValue(String number) throws IOException {
        length = number.length();
        if (number.charAt(0) == '-') {
            sign = Sign.MINUS;
            for (int i = length - 1; i >= 1; --i) {
                if (isDigit(number.charAt(i)))
                    digits.add((byte) (number.charAt(i) - '0'));
                else
                    throw new IOException("Incorrect long number");
            }
            length--;
        } else {
            if (number.charAt(0) == '+') {
                for (int i = length - 1; i >= 1; --i) {
                    if (isDigit(number.charAt(i)))
                        digits.add((byte) (number.charAt(i) - '0'));
                    else
                        throw new IOException("Incorrect long number");
                }
                length--;
            } else {
                for (int i = length - 1; i >= 0; --i) {
                    if (isDigit(number.charAt(i)))
                        digits.add((byte) (number.charAt(i) - '0'));
                    else
                        throw new IOException("Incorrect long number");
                }
            }
        }
    }

    public byte[] getDigits() {
        int i = 0;
        byte[] arr = new byte[digits.size()];
        for (Byte obj : digits) {
            arr[i++] = obj;
        }
        return arr;
    }

    public void setDigit(byte digit, int index) {
        int len = digits.size();
        while (index + 1 > len) {
            digits.add((byte) 0);
            len++;
        }
        digits.set(index, digit);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte getDigit(int index) {
        int len = digits.size();
        if (index + 1 > len)
            return (byte) 0;
        return digits.get(index);
    }

    public int getLength() {
        int i;
        for (i = digits.size() - 1; i >= 0; --i)
            if (digits.get(i) != 0)
                return i + 1;
        return 0;
    }

    public int getLengthMul() {
        return digits.size();
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < this.getLength(); ++i)
            s = this.digits.get(i) + s;
        if (StringUtils.isBlank(s))
            return "0";
        if (sign == Sign.MINUS)
            s = "-" + s;
        return s;
    }

    public int compareTo(LongArithmethic o) {
        int i = 0;
        int n = digits.size() > o.getLength() ? digits.size() : o.getLength();
        for (i = n - 1; i >= 0 && getDigit(i) == 0 && o.getDigit(i) == 0; --i) ;
        while (i >= 0 && getDigit(i) == o.getDigit(i))
            --i;
        if (i < 0)
            return 0;
        else if (getDigit(i) > o.getDigit(i))
            return 1;
        else
            return -1;
    }
}
