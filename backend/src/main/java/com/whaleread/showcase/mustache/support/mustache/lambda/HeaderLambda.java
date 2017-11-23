package com.whaleread.showcase.mustache.support.mustache.lambda;

/**
 * Expose page header to layout with name {@link #NAME}
 * Created by Dolphin on 2017/11/23
 */
public class HeaderLambda extends ExposeLambda {
    private static final String NAME = "__header__";

    @Override
    protected String getName() {
        return NAME;
    }
}
