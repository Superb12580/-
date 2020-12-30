package com.superb.service.impl;

import com.superb.entity.BookBorrow;
import com.superb.mapper.BookBorrowMapper;
import com.superb.service.BookBorrowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Superb
 * @since 2020-12-26
 */
@Service
public class
BookBorrowServiceImpl extends ServiceImpl<BookBorrowMapper, BookBorrow> implements BookBorrowService {

    @Autowired
    private BookBorrowMapper bookBorrowMapper;


    @Override
    public List<Map<String, Object>> borrowList() {

        return bookBorrowMapper.borrowList();
    }
}
