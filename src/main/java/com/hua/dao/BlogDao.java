package com.hua.dao;


import com.hua.dto.*;
import com.hua.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogDao {

    //根据博客ID查询dto里面的全部内容
    ShowBlog getBlogById(Long id);

    //查询所有dto层的BlogQuery包括type里面的所有值
    List<BlogQuery> getAllBlogQuery();

    //插入blog内容
    int saveBlog(Blog blog);

    //根据ID删除
    int deleteBlog(Long id);

    //更新Blog的内容根据Blog的ID
    int updateBlog(ShowBlog showBlog);

    //插入tag的id和blog的id
    int saveBlogAndTag(BlogAndTag blogAndTag);

    //删除tag的id和blog的id
    int deleteBlogAndTag(Long blogId);

    //搜索标题或者类型或者推荐
    List<BlogQuery> searchByTitleOrTypeOrRecommend(SearchBlog searchBlog);

    List<FirstPageBlog> getFirstPageBlog();

    List<RecommendBlog> getAllRecommendBlog();

    List<FirstPageBlog> getSearchBlog(String query);

    DetailedBlog getDetailedBlog(Long id);

    List<FirstPageBlog> getByTypeId(Long typeId);

    List<FirstPageBlog> getByTagId(Long tagId);
}
