package com.whaleread.showcase.mustache.support.mustache.lambda;

import org.springframework.stereotype.Component;

/**
 * expose page title to layout with name {@link #NAME}
 * Created by Dolphin on 2017/11/14
 */
@Component
public class TitleLambda extends ExposeLambda {
    private final String NAME = "__title__";

    @Override
    protected String getName() {
        return NAME;
    }
}
