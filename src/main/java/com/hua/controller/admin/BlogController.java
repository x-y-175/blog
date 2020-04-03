package com.hua.controller.admin;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hua.dto.BlogQuery;
import com.hua.dto.SearchBlog;
import com.hua.dto.ShowBlog;
import com.hua.pojo.Blog;
import com.hua.pojo.Tag;
import com.hua.pojo.Type;
import com.hua.pojo.User;
import com.hua.service.BlogService;
import com.hua.service.TagService;
import com.hua.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {



    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;


    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAdminType());
        model.addAttribute("tags", tagService.getAdminTag());
    }

    //显示
    @GetMapping("/blogs")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 3);
        //这里查了blogs展示所需要的内容，创建了dto，包括type
        List<BlogQuery> allBlog = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        //传入model进去，调用setTypeAndTag方法，然后前端可以调用types参数
        setTypeAndTag(model);
        return "admin/blogs";
    }


    //删除
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    //去新增页面
    @GetMapping("/blogs/input")
    public String toAdd(Model model) {
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    //新增
    @PostMapping("/blogs")
    public String add(Blog blog, RedirectAttributes attributes, HttpSession session) {

        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type (得到blog里面的Type里面的ID)，然后根据这个ID去typeService里面根据ID查询Type的所有东西
        //在根据这个ID插入进去Type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性（通过blog得到Type的ID属性,然后在存入Type）
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值（blog得到Tag标签ID，在根据Tag标签ID调用service的方法，）
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message", "新增成功");
        return "redirect:/admin/blogs";
    }

    //搜索
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        //将recommend转换一下
        blogService.transformRecommend(searchBlog);
        //动态sql可以解决
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        PageHelper.startPage(pageNum, 2);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        model.addAttribute("message", "查询成功");
        return "admin/blogs";
    }

    //将数据回返编辑页面
    @GetMapping("/blogs/{id}/input")
    public String toUpdate(@PathVariable Long id,Model model) {
        ShowBlog blogById = blogService.getBlogById(id);
        List<Type> allType = typeService.getAdminType();
        List<Tag> allTag = tagService.getAdminTag();
        model.addAttribute("blog", blogById);
        model.addAttribute("types", allType);
        model.addAttribute("tags", allTag);
        return "admin/blogs-update";
    }


    @PostMapping("/blogs/update")
    public String editPost(ShowBlog showBlog,RedirectAttributes attributes) {
        blogService.updateBlog(showBlog);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/blogs";
    }

}
