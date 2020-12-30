package com.superb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superb.entity.BookBorrow;
import com.superb.entity.BookInfo;
import com.superb.service.BookBorrowService;
import com.superb.service.BookInfoService;
import com.superb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Superb
 * @since 2020-12-26
 */
@Controller
@RequestMapping("/info")
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;



    /**
     * 展示所有书籍
     * @param model
     * @param request
     * @return
     */
    @GetMapping()
    public String index(Model model, HttpServletRequest request){

        //登录拦截
        if (request.getSession().getAttribute("username")==null){
            model.addAttribute("msg","未登录");
            return "login";
        }


        //2表查询 书类型
        List<Map<String, Object>> list = bookInfoService.infoList();


        model.addAttribute("books",list);
        return "book/info";
    }


    /**
     * 返回一本书信息
     * @param bookId
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public Result get(@RequestParam("id") Long bookId){
        BookInfo bookInfo = bookInfoService.getById(bookId);
        return Result.success(bookInfo);
    }


    /**
     * 添加
     * @param bookInfo
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(BookInfo bookInfo){
        bookInfoService.save(bookInfo);
        return Result.success("添加成功",null);
    }


    /**
     * 编辑
     * @param bookInfo
     * @return
     */
    @PutMapping("/put")
    @ResponseBody
    public Result put(BookInfo bookInfo){
        bookInfoService.updateById(bookInfo);
        return Result.success("编辑成功",null);
    }


    @Autowired
    private BookBorrowService bookBorrowService;

    @DeleteMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("ids") String ids){
        //批量
        if (ids.contains("-")){
            List<Long> list = new ArrayList<>();
            String[] bookIds = ids.split("-");
            for (String str : bookIds){
                list.add(Long.parseLong(str));
            }
            QueryWrapper<BookBorrow> bookBorrowQueryWrapper = new QueryWrapper<>();
            bookBorrowQueryWrapper.in("book_id", list).eq("real_date","0");
            List<BookBorrow> borrows = bookBorrowService.list(bookBorrowQueryWrapper);
            if (borrows != null && borrows.size() != 0){
                return Result.success("尚有图书借阅未归还，不能删除",null);
            }

            bookInfoService.removeByIds(list);
            return Result.success("批量删除成功",null);
        }
        //单个
        QueryWrapper<BookBorrow> bookBorrowQueryWrapper = new QueryWrapper<>();
        bookBorrowQueryWrapper.eq("book_id",ids).eq("real_date","0");
        List<BookBorrow> list = bookBorrowService.list(bookBorrowQueryWrapper);
        if (list != null && list.size() != 0){
            return Result.success("尚有图书借阅未归还，不能删除",null);
        }
        bookInfoService.removeById(Long.parseLong(ids));
        return Result.success("删除成功",null);

    }

}
