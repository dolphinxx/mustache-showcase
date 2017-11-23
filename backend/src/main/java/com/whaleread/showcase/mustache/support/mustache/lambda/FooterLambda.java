package com.whaleread.showcase.mustache.support.mustache.lambda;

import org.springframework.stereotype.Component;

/**
 * Expose page footer content to layout with name {@linkplain #NAME}
 * Created by Dolphin on 2017/11/23
 */
@Component
public class FooterLambda extends ExposeLambda {
    private static final String NAME = "__footer__";

    @Override
    protected String getName() {
        return NAME;
    }
}
