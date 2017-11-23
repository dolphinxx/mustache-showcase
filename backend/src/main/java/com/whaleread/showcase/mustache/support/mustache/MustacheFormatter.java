package com.whaleread.showcase.mustache.support.mustache;

import com.samskivert.mustache.Mustache;

import java.util.Date;

/**
 * Created by Dolphin on 2017/11/23
 */
public class MustacheFormatter implements Mustache.Formatter {
    @Override
    public String format(Object value) {
        if (Date.class.isAssignableFrom(value.getClass())) {
            return String.valueOf(((Date) value).getTime());
        }
        return value.toString();
    }
}
