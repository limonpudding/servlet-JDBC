package org;

enum Sign {
    PLUS, MINUS;
    public Sign reverse() { return this == PLUS ? MINUS : PLUS; }
}
