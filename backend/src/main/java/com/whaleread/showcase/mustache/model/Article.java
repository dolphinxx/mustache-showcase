package com.whaleread.showcase.mustache.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Created by Dolphin on 2017/11/22
 */
@Getter
@Setter
public class Article {
    private Long id;
    private String title;
    private String thumb;
    private Date createTime;
    private Integer visits;
    private List<String> tags;
    private String source;
    private String summary;
}
