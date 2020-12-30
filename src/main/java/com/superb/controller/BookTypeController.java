package com.superb.controller;


import com.superb.entity.BookType;
import com.superb.service.BookTypeService;
import com.superb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Superb
 * @since 2020-12-26
 */
@Controller
@RequestMapping("/type")
public class BookTypeController {

    @Autowired
    private BookTypeService bookTypeService;


    /**
     * 显示所有图书类型
     * @param model
     * @param request
     * @return
     */
    @GetMapping
    public String index(Model model, HttpServletRequest request) {

        //登录拦截
        if (request.getSession().getAttribute("username")==null){
            model.addAttribute("msg","未登录");
            return "login";
        }


        List<BookType> list = bookTypeService.list();
        model.addAttribute("types", list);
        return "book/type";
    }

    /**
     * 查询单个
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public Result get(@RequestParam("id") Long id) {
        BookType bookType = bookTypeService.getById(id);

        return Result.success(bookType);
    }

    /**
     * 填充下拉框
     *
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result list() {
        List<BookType> list = bookTypeService.list();
        return Result.success(list);
    }


    /**
     * 添加编辑
     * @param bookType
     * @param request
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Result add(BookType bookType, HttpServletRequest request) {
        //格式化当前日期
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        bookType.setCzsj(format);
        //保存提交人
        bookType.setCzr(request.getSession().getAttribute("username").toString());
        if (bookType.getId() == null) {
            bookTypeService.save(bookType);
            return Result.success("添加成功", null);
        }

        bookTypeService.updateById(bookType);
        return Result.success("修改成功", null);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("id") Integer id) {
        bookTypeService.removeById(id);
        return Result.success("删除成功", null);
    }

    /**
     * 批量
     * @param ids
     * @return
     */
    @DeleteMapping("/deletes")
    @ResponseBody
    public Result deletes(@RequestParam("ids") String ids) {
        List<Long> list = new ArrayList<>();
        String[] bookIds = ids.split("-");
        for (String str : bookIds) {
            list.add(Long.parseLong(str));
        }

        bookTypeService.removeByIds(list);
        return Result.success("批量删除成功", null);

    }

}
