package com.whaleread.showcase.mustache;

import com.whaleread.showcase.mustache.support.mustache.lambda.LayoutLambda;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * <table>
 * <tr>
 * <th>mapper</th>
 * <th>matched templates</th>
 * </tr>
 * <tr>
 * <td>_default_</td>
 * <td>**</td>
 * </tr>
 * <tr>
 * <td>index</td>
 * <td>index</td>
 * </tr>
 * <tr>
 * <td>category:index</td>
 * <td>category/index</td>
 * </tr>
 * <tr>
 * <td>category:_default_</td>
 * <td>category/**</td>
 * </tr>
 * </table>
 * Created by Dolphin on 2017/11/22
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mustache")
public class LayoutMapper {
    private static final String DEFAULT_KEY = "_default_";
    private Map<String, Object> layout;
    private Map<String, String> layoutMapper;

    @PostConstruct
    private void init() {
        System.out.println(layout);
        layoutMapper = new HashMap<>();
        flattenLayoutMapper(null, layout);
        layout.clear();
        layout = null;
    }

    private void flattenLayoutMapper(String parent, Map<String, ?> mapper) {
        for (Map.Entry<String, ?> entry : mapper.entrySet()) {
            if (entry.getValue() instanceof String) {// leaf
                if (entry.getKey().equals(DEFAULT_KEY)) {// default mapper
                    layoutMapper.put(parent, (String) entry.getValue());
                    continue;
                }
                layoutMapper.put(parent == null ? entry.getKey() : parent + '/' + entry.getKey(), (String) entry.getValue());
                continue;
            }
            //noinspection unchecked
            flattenLayoutMapper(parent == null ? entry.getKey() : parent + '/' + entry.getKey(), (Map<String, ?>) entry.getValue());
        }
    }

    public String getLayout(String templateName) {
        if (templateName.indexOf('/') == -1) {
            return layoutMapper.getOrDefault(templateName, LayoutLambda.DEFAULT_LAYOUT);
        }
        String layoutName = layoutMapper.get(templateName);
        if (layoutName != null) {
            return layoutName;
        }
        int index;
        int fromIndex = templateName.length();
        while ((index = templateName.lastIndexOf('/', fromIndex)) > 0) {
            layoutName = layoutMapper.get(templateName.substring(0, index));
            if (layoutName != null) {
                return layoutName;
            }
            fromIndex = index - 1;
        }
        return LayoutLambda.DEFAULT_LAYOUT;
    }
}
