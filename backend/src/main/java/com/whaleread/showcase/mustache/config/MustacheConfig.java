package com.whaleread.showcase.mustache.config;

import com.google.common.base.CaseFormat;
import com.samskivert.mustache.Mustache;
import com.whaleread.showcase.mustache.support.mustache.MustacheApplicationCollector;
import com.whaleread.showcase.mustache.support.mustache.MustacheFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * Created by Dolphin on 2017/11/22
 */
@Configuration
public class MustacheConfig {
    @Value("${static_base}")
    private String staticBase;

    private final Environment env;

    @Autowired
    public MustacheConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader mustacheTemplateLoader, Map<String, Mustache.Lambda> lambdas) {
        MustacheApplicationCollector collector = new MustacheApplicationCollector();
        collector.addVariable("static_base", staticBase);
        collector.addVariable("dev", env.acceptsProfiles("dev"));
        for (Map.Entry<String, Mustache.Lambda> lambda : lambdas.entrySet()) {
            String name = lambda.getKey();
            if (name.indexOf('_') == -1) {
                name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
            }
            collector.addVariable(name, lambda.getValue());
        }
        String defaultValue = env.acceptsProfiles("dev") ? "!!!!MISSING VALUE!!!!" : "";
        return Mustache.compiler().withFormatter(new MustacheFormatter()).defaultValue(defaultValue).withLoader(mustacheTemplateLoader)
                .withCollector(collector);
    }
}
