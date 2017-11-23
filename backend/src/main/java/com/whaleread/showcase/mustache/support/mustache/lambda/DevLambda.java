package com.whaleread.showcase.mustache.support.mustache.lambda;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

/**
 * Render inner body if spring dev profile is active.
 * Created by Dolphin on 2017/11/23
 */
@Component
public class DevLambda implements Mustache.Lambda {
    private final boolean dev;

    public DevLambda(Environment env) {
        this.dev = env.acceptsProfiles("dev");
    }

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        if (!dev) {
            frag.execute(out);
        }
    }
}
