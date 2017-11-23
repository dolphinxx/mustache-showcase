package com.whaleread.showcase.mustache.support.mustache.lambda;

import org.springframework.stereotype.Component;

/**
 * expose page nav name to the layout and in a layout template, we can get it through the name {@link #NAME}, eg: {{#nav_name_lambda}}Sample Page{{/nav_name_lambda}},
 * Created by Dolphin on 2017/11/14
 */
@Component
public class NavNameLambda extends ExposeLambda {
    static final String NAME = "__nav__";

    @Override
    protected String getName() {
        return NAME;
    }
}
