package com.whaleread.showcase.mustache.support.mustache;

import java.util.Iterator;

/**
 * Created by Dolphin on 2017/12/8
 */
public class DecoratedIterator<T> implements Iterator<DecoratedIteratorItem<T>> {
    private Iterator<T> iterator;
    private int index;

    public DecoratedIterator(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public DecoratedIteratorItem<T> next() {
        return new DecoratedIteratorItem<>(iterator.next(), index++, iterator.hasNext());
    }
}
