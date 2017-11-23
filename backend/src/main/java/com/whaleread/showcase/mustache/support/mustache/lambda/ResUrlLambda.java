package com.whaleread.showcase.mustache.support.mustache.lambda;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

/**
 * Convert relative resource path to absolute path prefixing ${res_base} on necessary
 * Created by Dolphin on 2017/11/22
 */
@Component
public class ResUrlLambda implements Mustache.Lambda {
    @Value("${res_base}")
    private String resourceBase;

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        out.write(formatUrl(frag.execute()));
    }

    private String formatUrl(String raw) {
        if (raw == null) {
            return "";
        }
        if (raw.startsWith("http:") || raw.startsWith("https:") || raw.startsWith("//")) {
            return raw;
        }
        return resourceBase + raw;
    }
}
