package com.whaleread.showcase.mustache.support.mustache;

import com.samskivert.mustache.DefaultCollector;
import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dolphin on 2017/11/15
 */
public class MustacheApplicationCollector extends DefaultCollector {
    private Map<String, Object> variables = new HashMap<>();
    private ApplicationVariableFetcher fetcher = new ApplicationVariableFetcher();

    @Override
    public Mustache.VariableFetcher createFetcher(Object ctx, String name) {
        if (variables.containsKey(name)) {
            return fetcher;
        }
        return super.createFetcher(ctx, name);
    }

    public void addVariable(String key, Object value) {
        variables.put(key, value);
    }

    public void addVariables(Map<String, ?> variables) {
        this.variables.putAll(variables);
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
