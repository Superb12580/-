package com.superb.service;

import com.superb.entity.BookBorrow;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Superb
 * @since 2020-12-26
 */
public interface BookBorrowService extends IService<BookBorrow> {

    List<Map<String, Object>> borrowList();

}
