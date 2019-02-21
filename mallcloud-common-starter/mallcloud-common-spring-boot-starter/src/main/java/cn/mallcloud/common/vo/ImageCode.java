package cn.mallcloud.common.vo;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 图形验证码
 *
 * @author zscat
 * @date 2017-12-18
 */
@Data
public class ImageCode implements Serializable {
    private static final long serialVersionUID = -7793702910139171647L;
    private String code;

    private LocalDateTime expireTime;

    private BufferedImage image;

    public ImageCode(BufferedImage image, String sRand, int defaultImageExpire) {
        this.image = image;
        this.code = sRand;
        this.expireTime = LocalDateTime.now().plusSeconds(defaultImageExpire);
    }
}
