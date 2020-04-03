package com.hua.dto;

import com.hua.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/*
 blogs页面中的
 <th>标题</th>
 <th>类型</th>
 <th>推荐</th>
 <th>更新时间</th>
 返回到这里
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private Integer recommend;
    private Long typeId;

    private Type type;
}
