package com.whaleread.showcase.mustache.support.mustache;

import com.samskivert.mustache.DefaultCollector;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Dolphin on 2017/11/15
 */
public class MustacheApplicationCollector extends DefaultCollector {
    private Map<String, Object> variables = new HashMap<>();
    private ApplicationVariableFetcher fetcher = new ApplicationVariableFetcher();

    @Override
    public Iterator<?> toIterator(Object value) {
        Iterator<?> iterator = super.toIterator(value);
        if (iterator != null) {
            return new DecoratedIterator<>(iterator);
        }
        return null;
    }

    @Override
    public Mustache.VariableFetcher createFetcher(Object ctx, String name) {
        if (variables.containsKey(name)) {
            return fetcher;
        }
        // DecoratedIteratorItem holds iteration states
        if (DecoratedIteratorItem.class.isAssignableFrom(ctx.getClass())) {
            if (name.charAt(0) == '_' && name.charAt(name.length() - 1) == '_') {
                return (ctx1, name1) -> getMethod(DecoratedIteratorItem.class, name1).invoke(ctx1);
            }
            return getDecoratedIteratorItemFetcher(((DecoratedIteratorItem<?>) ctx).getValue(), name);
        }
        return super.createFetcher(ctx, name);
    }

    public void addVariable(String key, Object value) {
        variables.put(key, value);
    }

    public void addVariables(Map<String, ?> variables) {
        this.variables.putAll(variables);
    }

    protected static final String DOT_NAME = ".".intern();
    protected static final String THIS_NAME = "this".intern();

    private Mustache.VariableFetcher getDecoratedIteratorItemFetcher(Object ctx, String name) {
        // copied from BasicCollector#createFetcher
        // support both .name and this.name to fetch members
        if (name == DOT_NAME || name == THIS_NAME) return THIS_FETCHER;

        if (ctx instanceof Map<?, ?>) return MAP_FETCHER;

        // if the name looks like a number, potentially use one of our 'indexing' fetchers
        char c = name.charAt(0);
        if (c >= '0' && c <= '9') {
            if (ctx instanceof List<?>) return LIST_FETCHER;
            if (ctx instanceof Iterator<?>) return ITER_FETCHER;
            if (ctx.getClass().isArray()) return arrayHelper(ctx);
        }

        // copied from DefaultCollector#createFetcher
        // first check for a getter which provides the value
        Class<?> cclass = ctx.getClass();
        final Method m = getMethod(cclass, name);
        if (m != null) {
            return new Mustache.VariableFetcher() {
                public Object get(Object ctx, String name) throws Exception {
                    if (DecoratedIteratorItem.class.isAssignableFrom(ctx.getClass())) {
                        return m.invoke(((DecoratedIteratorItem) ctx).getValue());
                    }
                    return m.invoke(ctx);
                }
            };
        }

        // next check for a getter which provides the value
        final Field f = getField(cclass, name);
        if (f != null) {
            return new Mustache.VariableFetcher() {
                public Object get(Object ctx, String name) throws Exception {
                    if (DecoratedIteratorItem.class.isAssignableFrom(ctx.getClass())) {
                        return f.get(((DecoratedIteratorItem) ctx).getValue());
                    }
                    return f.get(ctx);
                }
            };
        }

        // finally check for a default interface method which provides the value (this is left to
        // last because it's much more expensive and hopefully something already matched above)
        final Method im = getIfaceMethod(cclass, name);
        if (im != null) {
            return new Mustache.VariableFetcher() {
                public Object get(Object ctx, String name) throws Exception {
                    if (DecoratedIteratorItem.class.isAssignableFrom(ctx.getClass())) {
                        return im.invoke(((DecoratedIteratorItem) ctx).getValue());
                    }
                    return im.invoke(ctx);
                }
            };
        }

        return null;
    }

    private class ApplicationVariableFetcher implements Mustache.VariableFetcher {
        private ApplicationVariableFetcher() {
        }

        @Override
        public Object get(Object ctx, String name) throws Exception {
            Object value = variables.get(name);
            if (value != null) {
                return value;
            }
            return Template.NO_FETCHER_FOUND;
        }
    }
}
