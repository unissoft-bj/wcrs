/**
 * 
 */
package net.wyun.wcrs.controller.wechat;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import net.wyun.wcrs.jsj.JSJFormInfo;
import net.wyun.wcrs.model.Gender;
import net.wyun.wcrs.model.PAUser;
import net.wyun.wcrs.model.WCUser;
import net.wyun.wcrs.model.WCUserStatus;
import net.wyun.wcrs.model.WechatEvent;
import net.wyun.wcrs.model.repo.PAUserRepository;
import net.wyun.wcrs.model.repo.WCUserRepository;
import net.wyun.wcrs.model.repo.WechatEventRepository;
import net.wyun.wcrs.service.TokenService;
import net.wyun.wcrs.wechat.AdvancedUtil;
import net.wyun.wcrs.wechat.CommonUtil;
import net.wyun.wcrs.wechat.MessageUtil;
import net.wyun.wcrs.wechat.SignUtil;
import net.wyun.wcrs.wechat.message.event.QRCodeEvent;
import net.wyun.wcrs.wechat.message.req.ReqTextMessage;
import net.wyun.wcrs.wechat.message.resp.RespTextMessage;
import net.wyun.wcrs.wechat.po.SNSUserInfo;
import net.wyun.wcrs.wechat.po.WeixinOauth2Token;
import net.wyun.wcrs.wechat.po.WeixinUserInfo;

/**
 * @author Xuecheng
 *
 */
@RequestMapping("/wechat")
@Controller
public class OAuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(OAuthController.class);
	
	@Autowired
	PAUserRepository paUserRepo;
	
	@Autowired
	WCUserRepository userRepo;
	
	@Autowired
	WechatEventRepository weRepo;
	
	@Autowired
	TokenService tokenService;
	
	private final static String DENIED = "authdeny";
	@RequestMapping(value= "/oauth", method=RequestMethod.GET)
	String auth(/*@RequestBody String data, */ HttpServletRequest request) throws UnsupportedEncodingException{
		logger.info("Wechat OAuth from {}", request.getRemoteAddr());
		
		String openId = null;
		request.setCharacterEncoding("utf-8");
		
		String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.info("code {} and state {}", code, state);
        if(null == code){
        	throw new RuntimeException("wechat authorization error, null value code returned!!!");
        }
        if (!DENIED.equals(code)) {
        	logger.info("OAuth successful.");
        	
            // access_token verification to wechat server
        	WeixinOauth2Token weixinOauth2Token = null;
        	try{
        		weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wx479cc0c5b93538df", "9cef9bc43e6ab4a2feedbfd3bf5b1dff", code);
        	}catch (Exception e) {
        		logger.error("获取网页授权凭证失败", e);
        		throw new RuntimeException("wechat authorization error, 获取网页授权凭证失败!!!");
			}
            
            String accessToken = weixinOauth2Token.getAccessToken();
            openId = weixinOauth2Token.getOpenId();
            SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
            
            logger.info("openId {}", openId);
            
            HttpSession session=request.getSession();  
            session.setAttribute("openId",openId);  
            
            request.setAttribute("openId", openId);
            request.setAttribute("snsUserInfo", snsUserInfo);
            request.setAttribute("state", state);
        }else{
        	throw new RuntimeException("wechat authorization error, authorization denied!!!");
        }
		
        if(openId == null){
        	//go to error page
        	throw new RuntimeException("wechat authorization error, null openId!!!");
        }
        
        PAUser paU = this.paUserRepo.findByOpenId(openId);
        if(null == paU) {
        	throw new RuntimeException("wechat authorization error, paUser for openId not exist!!!");
        }
        
        if(paU.getUser().getStatus() == WCUserStatus.REGISTERED){
        	//to user page
        	HttpSession session=request.getSession();  
            session.setAttribute("openId",openId==null?"your oid not found":openId);  
    		return "redirect:/index.html";
        }else{
        	String redirectUrl = "https://jinshuju.net/f/JN3P8g";
        	return "redirect:" + redirectUrl;
        }
        
        
	}
	
}
