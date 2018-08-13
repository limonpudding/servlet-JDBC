package org;

class Operation {
    String a;
    String b;
    String operation;
    String result;

    Operation(String a, String b, String operation, String result) {
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.result = result;
    }

    @Override
    public String toString() {
        if (operation.equals("fib"))
            return operation + "(" + a + ") = " + result;
        return a + " " + operation + " " + b + " = " + result;
    }
}
