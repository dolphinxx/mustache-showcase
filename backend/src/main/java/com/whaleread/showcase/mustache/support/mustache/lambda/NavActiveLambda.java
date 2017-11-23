package com.whaleread.showcase.mustache.support.mustache.lambda;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Render the class name <code>active</code> while the variable <code>__nav__</code> is equal to this lambda's inner body<br/>
 * Created by Dolphin on 2017/11/23
 */
@Component
public class NavActiveLambda implements Mustache.Lambda {
    private static final String ACTIVE_CLASS_NAME = "active";

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        String body = frag.execute();
        if (body.equals(((Map) frag.context()).get(NavNameLambda.NAME))) {
            out.write(ACTIVE_CLASS_NAME);
        }
    }
}
