package net.wyun.wcrs.controller.wechat;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	private static final Logger logger = LoggerFactory.getLogger(JssdkController.class);
	
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
	@CrossOrigin
    @RequestMapping(value="/jssdk", method=RequestMethod.GET)
    public Message JSSDK_config( @RequestParam(value = "url", required = true) String url,
    		                     @RequestParam(value = "paId") String paId,
    		                     HttpServletRequest request) {
        try {
            logger.debug("url" + url);
            
            String url2Sign = this.getUrl(request);
            Map<String, String> configMap = jssdkConfig.jsSDK_Sign(url2Sign, paId);
            return Message.success(configMap);
        } catch (Exception e) {
        	e.printStackTrace();
            return Message.error();
        }
 
    }
    
    private String getUrl(HttpServletRequest request){
        StringBuffer requestUrl = request.getRequestURL();
         
        String queryString = request.getQueryString();
        String url = requestUrl +"?"+queryString;
        return url;
    }
 
}
