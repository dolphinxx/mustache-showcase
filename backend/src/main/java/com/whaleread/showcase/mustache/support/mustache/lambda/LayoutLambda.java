package com.whaleread.showcase.mustache.support.mustache.lambda;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import com.whaleread.showcase.mustache.LayoutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Choose a layout for the page, and use it to decorate the page
 * Created by Dolphin on 2017/11/14
 */
@Component
public class LayoutLambda implements Mustache.Lambda {
    public static final String DEFAULT_LAYOUT = "_layout";

    @Lazy
    @Autowired
    @SuppressWarnings("SpringAutowiredFieldsWarningInspection")
    private Mustache.Compiler compiler;
    private final LayoutMapper layoutMapper;

    public LayoutLambda(LayoutMapper layoutMapper) {
        this.layoutMapper = layoutMapper;
    }

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        //noinspection unchecked
        Map<String, Object> context = (Map<String, Object>) frag.context();
        context.put("__body__", frag.execute());
        compiler.compile("{{>" + layoutMapper.getLayout((String) context.get("__template_name__")) + "}}").execute(frag.context(), out);
    }
}
