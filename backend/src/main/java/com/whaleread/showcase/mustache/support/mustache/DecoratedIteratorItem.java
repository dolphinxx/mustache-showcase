package com.whaleread.showcase.mustache.support.mustache;

/**
 * Created by Dolphin on 2017/12/8
 */
public class DecoratedIteratorItem<T> {
    private int index;
    private boolean hasNext;

    private T value;

    public DecoratedIteratorItem(T value, int index, boolean hasNext) {
        this.value = value;
        this.index = index;
        this.hasNext = hasNext;
    }

    public int get_index_() {
        return index;
    }

    public boolean is_has_next_() {
        return hasNext;
    }

    public boolean is_odd_() {
        return index % 2 == 0;
    }

    public boolean is_even_() {
        return index % 2 == 1;
    }

    public T getValue() {
        return value;
    }
}
