package org;

public abstract class Lazy <T> {
    private T value;

    protected abstract T setValue();

    public T getValue() {
        if(value == null){
            value = setValue();
        }
        return value;
    }
}
