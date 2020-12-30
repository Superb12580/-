package com.superb.mapper;

import com.superb.entity.BookBorrow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Superb
 * @since 2020-12-26
 */
@Repository
public interface BookBorrowMapper extends BaseMapper<BookBorrow> {



    List<Map<String, Object>> borrowList();


}
