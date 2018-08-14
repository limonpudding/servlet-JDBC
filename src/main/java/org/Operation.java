package org;

class Operation {
    String a;
    String b;
    String operation;
    String result;
    String date;

    Operation(String date, String a, String b, String operation, String result) {
        this.date = date;
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.result = result;
    }

    @Override
    public String toString() {
        if (operation.equals("fib"))
            return operation + "(" + a + ") = " + result;
        return date + ": " + a + " " + operation + " " + b + " = " + result;
    }
}
