package org;

class Operation {
    String a;
    String b;
    String operation;
    String result;
    String date;
    String idOperation;

    Operation(String date, String a, String b, String operation, String result, String idOperation) {
        this.date = date;
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.result = result;
        this.idOperation = idOperation;
    }

    @Override
    public String toString() {
        if (operation.equals("fib"))
            return date + ": " + operation + "(" + a + ") = " + result;
        return date + ": " + a + " " + operation + " " + b + " = " + result;
    }
}
