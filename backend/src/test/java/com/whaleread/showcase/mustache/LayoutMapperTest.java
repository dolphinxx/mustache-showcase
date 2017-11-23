package com.whaleread.showcase.mustache;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dolphin on 2017/11/23
 */
@RunWith(JUnitParamsRunner.class)
public class LayoutMapperTest {
    private static LayoutMapper layoutMapper;

    @BeforeClass
    public static void setUp() throws Exception {
        layoutMapper = new LayoutMapper();
        YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
        yamlMapFactoryBean.setResources(new FileSystemResource(LayoutMapperTest.class.getResource("layout.yml").getPath()));
//        String layout = "" +
//                "category:\n" +
//                "  _default_: \"category/_layout\"\n" +
//                "food:\n" +
//                "  detail: \"food/_layout\"\n" +
//                "boo:\n" +
//                "  _default_: \"boo/_layout\"\n" +
//                "  index: \"_boo\"\n" +
//                "  foo:\n" +
//                "    detail: \"boo/_detail\"" +
//                "";
//        yamlMapFactoryBean.setResources(new ByteArrayResource(layout.getBytes("UTF-8")));
        yamlMapFactoryBean.afterPropertiesSet();
        ReflectionTestUtils.setField(layoutMapper, "layout", yamlMapFactoryBean.getObject());
        ReflectionTestUtils.invokeMethod(layoutMapper, "init");
    }

    public static Object[] parametersForGetLayout() {
        return new Object[][]{
                new Object[]{"index", "_layout"},
                new Object[]{"foo", "_layout"},
                new Object[]{"boo/index", "_boo"},
                new Object[]{"boo", "boo/_layout"},
                new Object[]{"boo/detail", "boo/_layout"},
                new Object[]{"boo/foo", "boo/_layout"},
                new Object[]{"boo/foo/detail", "boo/_detail"},
                new Object[]{"boo/foo/another", "boo/_layout"},
        };
    }

    @Test
    @Parameters
    public void getLayout(String template, String expected) throws Exception {
        assertEquals(expected, layoutMapper.getLayout(template));
    }

}
