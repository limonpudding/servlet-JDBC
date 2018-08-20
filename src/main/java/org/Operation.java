package org;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

class Operation {
    @JsonProperty("a")
    String a;
    @JsonProperty("b")
    String b;
    @JsonProperty("operation")
    String operation;
    @JsonProperty("result")
    String result;
    @JsonIgnore
    Date date;
    @JsonIgnore
    String idOperation;
    @JsonIgnore
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");

    /**
     * Конструктор для взятия операции на обработку
     * @param a
     * @param b
     * @param operation
     */
    Operation(String a, String b, String operation) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    Operation(){

    }

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
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a");
        if (operation.equals("fib"))
            return formatForDateNow.format(date) + ": " + operation + "(" + a + ") = " + result;
        return formatForDateNow.format(date) + ": " + a + " " + operation + " " + b + " = " + result;
    }
}
