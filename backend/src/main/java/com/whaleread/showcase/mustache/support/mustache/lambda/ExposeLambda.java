package com.whaleread.showcase.mustache.support.mustache.lambda;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Expose body to the current context as a named variable.
 * Created by Dolphin on 2017/11/23
 */
public abstract class ExposeLambda implements Mustache.Lambda {
    /**
     * The name used to expose the variable.
     */
    protected abstract String getName();

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        //noinspection unchecked
        ((Map<String, Object>) frag.context()).put(getName(), frag.execute());
    }
}
