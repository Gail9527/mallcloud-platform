package com.mallplus.marking.mapper;

import com.mallplus.marking.entity.SmsCouponHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mallplus.marking.vo.SmsCouponHistoryDetail;

import java.util.List;

/**
 * <p>
 * 优惠券使用、领取历史表 Mapper 接口
 * </p>
 *
 * @author zscat
 * @since 2019-04-19
 */
public interface SmsCouponHistoryMapper extends BaseMapper<SmsCouponHistory> {

    List<SmsCouponHistoryDetail> getDetailList(Long memberId);
}
