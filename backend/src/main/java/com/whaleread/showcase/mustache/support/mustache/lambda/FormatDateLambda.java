package com.whaleread.showcase.mustache.support.mustache.lambda;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Format date<br/>
 * usage: <br/>
 * <code>&lt;date&gt;|[&lt;format&gt;]</code><br/>
 * eg: <br/>
 * <code>{{#format_date_lambda}}{{date}}|yyyy-MM-dd HH:mm:ss{{/format_date_lambda}}</code><br/>
 * <code>{{#format_date_lambda}}{{date}}{{/format_date_lambda}}</code>(using the default format {@link #DEFAULT_FORMAT})<br/>
 * Created by Dolphin on 2017/11/23
 */
@Slf4j
@Component
public class FormatDateLambda implements Mustache.Lambda {
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        String body = frag.execute();
        int split = body.indexOf('|');
        String expr;
        String format;
        if (split == -1) {
            expr = body.trim();
            format = DEFAULT_FORMAT;
        } else {
            expr = body.substring(0, split).trim();
            format = body.substring(split + 1).trim();
        }
        if(expr.length() == 0) {
            log.debug("date_format_lambda param date is empty!");
            return;
        }
        out.write(new SimpleDateFormat(format).format(new Date(Long.parseLong(expr))));
    }
}
