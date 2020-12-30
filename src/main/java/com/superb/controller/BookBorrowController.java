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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Superb
 * @since 2020-12-26
 */
@Controller
@RequestMapping("/borrow")
public class BookBorrowController {

    @Autowired
    private BookBorrowService bookBorrowService;

    @Autowired
    private BookInfoService bookInfoService;

    @GetMapping()
    public String index(Model model, HttpServletRequest request) {
        //登录拦截
        if (request.getSession().getAttribute("username")==null){
            model.addAttribute("msg","未登录");
            return "login";
        }


        //3表查询 书名 书类型
        List<Map<String, Object>> list = bookBorrowService.borrowList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //滞纳金
        for (Map<String, Object> map : list) {
            //如果未还书 算出滞纳金返回前台展示 此时数据库实际为0
            if ("0".equals(map.get("real_date"))) {
                Date end_date = new Date();
                try {
                    end_date = sdf.parse(map.get("end_date").toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long l = LocalDate.now().toEpochDay();

                Instant instant = end_date.toInstant();
                ZoneId zoneId = ZoneId.systemDefault();
                LocalDate localDate = instant.atZone(zoneId).toLocalDate();
                long l1 = localDate.toEpochDay();
                if (l-l1>0){
                    map.put("latefee",l-l1);
                }

            }
        }

        model.addAttribute("borrows", list);
        return "book/borrow";
    }


    /**
     * 借阅
     *
     * @param bookBorrow
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Result save(BookBorrow bookBorrow) {
        //校验
        int i = bookBorrow.getBeginDate().compareTo(bookBorrow.getEndDate());
        if (i > 0) {
            return Result.fail("计划还书日期应在借阅日期之后");
        }
        //
        QueryWrapper<BookBorrow> bookBorrowQueryWrapper = new QueryWrapper<>();
        bookBorrowQueryWrapper.eq("book_id", bookBorrow.getBookId()).eq("borrower_name", bookBorrow.getBorrowerName()).eq("borrower_phone", bookBorrow.getBorrowerPhone()).eq("real_date","0");
        List<BookBorrow> list = bookBorrowService.list(bookBorrowQueryWrapper);
        if (list != null && list.size() != 0) {
            return Result.fail("该用户已借阅本书且尚未归还，不能重复借阅");
        }

        //借出 库存-1
        BookInfo bookInfo = bookInfoService.getById(bookBorrow.getBookId());
        bookInfo.setStock(bookInfo.getStock() - 1);
        bookInfoService.updateById(bookInfo);

        //保存借阅信息
        bookBorrowService.save(bookBorrow);
        return Result.success("借阅成功", null);
    }

    /**
     * 还书编辑
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public Result get(@RequestParam("id") Long id) {
        //查出记录
        BookBorrow bookBorrow = bookBorrowService.getById(id);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        //设置当前日期为实际还书日期 返回到前台
        bookBorrow.setRealDate(format);
        return Result.success(bookBorrow);
    }

    /**
     * 还书
     *
     * @param bookBorrow
     * @return
     */
    @PutMapping("/put")
    @ResponseBody
    public Result edit(BookBorrow bookBorrow) {
        //还书
        bookBorrowService.updateById(bookBorrow);
        //还书库存+1
        BookInfo bookInfo = bookInfoService.getById(bookBorrow.getBookId());
        bookInfo.setStock(bookInfo.getStock()+1);
        bookInfoService.updateById(bookInfo);

        return Result.success("还书成功", null);
    }

}
