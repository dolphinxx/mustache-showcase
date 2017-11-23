package com.whaleread.showcase.mustache.support.mustache.lambda;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

/**
 * simple ternary expression, no escape yet, eg:
 * <ul>
 *     <li><code>{{#ternary_lambda}}{{value1}}={{value2}}?{{value3}}:{{value4}}{{/ternary_lambda}}</code> =&gt; <code>value1.equals(value2)?value3:value4</code></li>
 *     <li><code>{{#ternary_lambda}}{{value1}}?{{value3}}:{{value4}}{{/ternary_lambda}}</code> =&gt; <code>value1.length == 0 || value1.equals("false") ? value4 : value3</code></li>
 * </ul>
 *
 * Created by Dolphin on 2017/11/23
 */
@Slf4j
@Component
public class TernaryLambda implements Mustache.Lambda {
    @Override
    public void execute(Template.Fragment frag, Writer out) throws IOException {
        String expr = frag.execute();
        int questIndex = expr.indexOf('?');
        if (questIndex == -1) {
            log.warn("Illegal ternary_lambda expression: {}", expr);
            return;
        }
        String compareExpr = expr.substring(0, questIndex).trim();
        int elseIndex = expr.indexOf(':');
        String value;
        String elseValue;
        if (elseIndex == -1) {
            value = expr.substring(questIndex + 1);
            elseValue = "";
        } else {
            value = expr.substring(questIndex + 1, elseIndex);
            elseValue = expr.substring(elseIndex + 1);
        }
        int compareIndex = compareExpr.indexOf('=');
        String result;
        if (compareIndex == -1) {
            result = compareExpr.length() == 0 || compareExpr.equals("false") ? elseValue : value;
        } else {
            result = compareExpr.substring(0, compareIndex).equals(compareExpr.substring(compareIndex + 1)) ? value : elseValue;
        }
        out.write(result);
    }
}
