package net.wyun.wcrs.controller.wechat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.wyun.wcrs.wechat.jssdk.JssdkConfig;
import net.wyun.wcrs.wechat.jssdk.Message;
 
/**
 * ClassName: WeChatController
 * @Description: 前端用户微信配置获取
 * @author dapengniao
 * @date 2016年3月19日 下午5:57:36
 */
@RestController
@RequestMapping("/secure")
public class JssdkController {
	
	@Autowired
	JssdkConfig jssdkConfig;
 
    /**
     * @Description: 前端获取微信JSSDK的配置参数
     * @param @param response
     * @param @param request
     * @param @param url
     * @param @throws Exception
     * @author dapengniao
     * @date 2016年3月19日 下午5:57:52
     */
    @RequestMapping(value="/jssdk", method=RequestMethod.GET)
    public Message JSSDK_config( @RequestParam(value = "url", required = true) String url,
    		                     @RequestParam(value = "paId") String paId) {
        try {
            System.out.println(url);
            Map<String, String> configMap = jssdkConfig.jsSDK_Sign(url, paId);
            return Message.success(configMap);
        } catch (Exception e) {
        	e.printStackTrace();
            return Message.error();
        }
 
    }
 
}
