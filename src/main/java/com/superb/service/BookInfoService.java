package com.superb.service;

import com.superb.entity.BookInfo;
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
public interface BookInfoService extends IService<BookInfo> {

    List<Map<String, Object>> infoList();

}
