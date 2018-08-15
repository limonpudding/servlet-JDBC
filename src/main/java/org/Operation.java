package org;

import java.text.SimpleDateFormat;
import java.util.Date;

class Operation {
    String a;
    String b;
    String operation;
    String result;
    Date date;
    String idOperation;
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    Operation(Date date, String a, String b, String operation, String result, String idOperation) {
        this.date = date;
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.result = result;
        this.idOperation = idOperation;
    }

    public String date(){
        return formatForDateNow.format(date);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        if (operation.equals("fib"))
            return formatForDateNow.format(date) + ": " + operation + "(" + a + ") = " + result;
        return formatForDateNow.format(date) + ": " + a + " " + operation + " " + b + " = " + result;
    }
}
