package com.mingyun.web.controller.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;
import com.mingyun.common.config.PmsConfig;
import com.mingyun.common.constant.CacheConstants;
import com.mingyun.common.constant.Constants;
import com.mingyun.common.core.domain.AjaxResult;
import com.mingyun.common.core.redis.RedisCache;
import com.mingyun.common.utils.sign.Base64;
import com.mingyun.common.utils.uuid.IdUtils;
import com.mingyun.system.service.ISysConfigService;

/**
 * 验证码操作处理
 * 
 * @author mingyun
 */
@RestController
public class CaptchaController
{
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;
    
    @Autowired
    private ISysConfigService configService;
    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode() throws IOException
    {
        //创建一个成功的返回对象
        AjaxResult ajax = AjaxResult.success();
        //查询系统设置里面是否开启验证码
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        //不管开否与否。把状态交给客户端
        ajax.put("captchaEnabled", captchaEnabled);
        //如查没有开启验证码，就直接返回 不用生成验证码了
        if (!captchaEnabled)
        {
            return ajax;
        }
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();//生成UUID
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;//组装Key

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = PmsConfig.getCaptchaType();
        //根据yml里面配置的生成方式来创建验证码
        if ("math".equals(captchaType))
        {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        }
        else if ("char".equals(captchaType))
        {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }
        //把正确的验证码放到redis
        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return AjaxResult.error(e.getMessage());
        }
        //响应uuid和图片的字符串到客户端
        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }
}
