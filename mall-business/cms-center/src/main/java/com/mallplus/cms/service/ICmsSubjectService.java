package com.mallplus.cms.service;

import com.mallplus.cms.entity.CmsSubject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 专题表 服务类
 * </p>
 *
 * @author zscat
 * @since 2019-04-17
 */
public interface ICmsSubjectService extends IService<CmsSubject> {

    int updateRecommendStatus(Long ids, Integer recommendStatus);

    int updateShowStatus(Long ids, Integer showStatus);

    boolean saves(CmsSubject entity);
}
