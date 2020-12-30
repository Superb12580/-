package com.superb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Superb
 * @since 2020-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BookBorrow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 图书id
     */
    private Long bookId;

    /**
     * 借阅人姓名
     */
    private String borrowerName;

    /**
     * 借阅人姓手机号
     */
    private String borrowerPhone;

    /**
     * 借阅时间
     */
    private String beginDate;

    /**
     * 计划还书时间
     */
    private String endDate;

    /**
     * 实际还书时间
     */
    private String realDate;

    /**
     * 滞纳金
     */
    private Long latefee;


}
