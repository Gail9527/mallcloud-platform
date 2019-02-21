package cn.mallcloud.rbac.admin.controller;

import cn.mallcloud.common.constants.SecurityConstants;
import cn.mallcloud.rbac.admin.service.SysUserService;
import com.google.code.kaptcha.Producer;
import com.xiaoleilu.hutool.lang.Assert;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author zscat
 * @date 2017/12/18
 * 验证码提供
 */
@Controller
public class ValidateCodeController {
    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService userService;

    /**
     * 创建验证码
     *
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{randomStr}")
    public void createCode(@PathVariable String randomStr, HttpServletResponse response)
            throws Exception {
        Assert.notEmpty(randomStr, "机器码不能为空");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        userService.saveImageCode(randomStr, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        IOUtils.closeQuietly(out);
    }
}
