package com.dista.cours.validation.visitor.lookups;

public class LengthWrapper {
    final int min;
    final int max;

    public LengthWrapper(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }
}
