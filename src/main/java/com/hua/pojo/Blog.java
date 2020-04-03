package com.hua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String title;           //标题
    private String content;         //博客内容
    private String firstPicture;    //首图
    private String flag;            //标记
    private Integer views;          //浏览次数
    private boolean appreciation;   //是否赞赏
    private boolean shareStatement; //是否分享
    private boolean commentabled;    //是否开启评论
    private boolean published;     //是否发布还是保存草稿
    private boolean recommend;    //是否推荐
    private Date createTime;        //创建时间
    private Date updateTime;        //更新时间
    //这个属性用来在mybatis中进行连接查询的
    private Long typeId;
    private Long userId;
    //获取多个标签的id
    private String tagIds;
    private String description;

    private Type type;

    private User user;

    private List<Tag> tags = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

}
