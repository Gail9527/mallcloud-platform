package com.mallplus.goods.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: shenzhuan
 * @Date: 2019/2/23 13:12
 * @Description:
 */
@Data
public class ConsultTypeCount implements Serializable{
    private  int all;
    private  int goods;
    private  int general;
    private  int bad;
    private  int other;

}
