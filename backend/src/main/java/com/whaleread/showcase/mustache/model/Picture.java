package com.whaleread.showcase.mustache.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dolphin on 2017/11/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    private Long id;
    private String title;
    private String thumb;
    private String source;
    private Integer likeCount;
    private Date createTime;
}
