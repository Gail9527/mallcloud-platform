package cn.mallcloud.admin.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 服务实体
 *
 * @author zscat
 * @date 2017/11/20 9:41
 */
@Data
public class Application implements Serializable {

    private static final long serialVersionUID = 6719219594944532374L;

    /**
     * 服务id
     */
    private String serviceId;

    /**
     * 服务实例集合
     */
    private Collection<Instance> instances = new ArrayList<>();
}
