package com.whaleread.showcase.mustache.support.mustache.lambda;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Add variable to the current context, split by <code>|</code>, the former is the name of the variable, and the latter is the value<br/>
 * ex: <code>{{#variable_lambda}}foo|123456{{/variable_lambda}}</code> will add foo=123456 to the context
 * Created by Dolphin on 2017/11/14
 */
@Slf4j
@Component
public class VariableLambda implements Mustache.Lambda {

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        String text = frag.execute();
        int split = text.indexOf('|');
        if (split == -1) {
            log.warn("Illegal variable_lambda expression: {}", text);
            return;
        }
        //noinspection unchecked
        ((Map<String, Object>) frag.context()).put(text.substring(0, split), text.substring(split + 1));
    }
}
